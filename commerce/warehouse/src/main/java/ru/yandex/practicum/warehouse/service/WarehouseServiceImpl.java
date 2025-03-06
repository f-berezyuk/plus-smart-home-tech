package ru.yandex.practicum.warehouse.service;

import java.util.Map;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.shopping.store.dto.ProductDto;
import ru.yandex.practicum.shopping.store.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.shopping.store.enums.QuantityState;
import ru.yandex.practicum.shopping.store.feign.ShoppingStoreClient;
import ru.yandex.practicum.warehouse.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.common.dto.AddressDto;
import ru.yandex.practicum.warehouse.dto.AssemblyProductForOrderFromShoppingCartRequest;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;
import ru.yandex.practicum.warehouse.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.warehouse.entity.Booking;
import ru.yandex.practicum.warehouse.entity.WarehouseProduct;
import ru.yandex.practicum.warehouse.exception.NoSpecifiedProductInWarehouseException;
import ru.yandex.practicum.warehouse.exception.ProductInShoppingCartLowQuantityInWarehouse;
import ru.yandex.practicum.warehouse.exception.SpecifiedProductAlreadyInWarehouseException;
import ru.yandex.practicum.warehouse.mapper.BookingMapper;
import ru.yandex.practicum.warehouse.mapper.WarehouseMapper;
import ru.yandex.practicum.warehouse.repository.BookingRepository;
import ru.yandex.practicum.warehouse.repository.WarehouseRepository;

import static ru.yandex.practicum.common.dto.AddressDto.getDefaultAddress;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private static final int LIMIT_COUNT = 5;
    private static final int ENOUGH_COUNT = 20;
    private final WarehouseRepository warehouseProductRepository;
    private final BookingRepository bookingRepository;
    private final ShoppingStoreClient shoppingStoreClient;
    private final WarehouseMapper warehouseMapper;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public void addNewProduct(NewProductInWarehouseRequest request) {
        log.info("addNewProduct {}", request);

        if (warehouseProductRepository.existsById(request.getProductId())) {
            throw new SpecifiedProductAlreadyInWarehouseException("Product already exist");
        }

        ProductDto productDto = shoppingStoreClient.findProductById(String.valueOf(request.getProductId()));
        if (productDto == null) {
            throw new RuntimeException("product not found");
        }

        WarehouseProduct newProduct = warehouseMapper.toWarehouseProduct(request);
        newProduct.setQuantityAvailable(0);

        warehouseProductRepository.save(newProduct);
    }

    @Override
    @Transactional
    public void acceptReturn(Map<UUID, Integer> products) {
        log.info("acceptReturn {}", products);

        products.forEach((productId, quantity) -> {
            WarehouseProduct product = warehouseProductRepository.findById(productId)
                    .orElseThrow(() -> new NoSpecifiedProductInWarehouseException("product not found"));
            product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
            warehouseProductRepository.save(product);
        });
    }

    @Override
    @Transactional
    public BookedProductsDto bookProductForShoppingCart(ShoppingCartDto shoppingCart) {
        log.info("bookProductForShoppingCart {}", shoppingCart);

        double totalWeight = 0;
        double totalVolume = 0;
        boolean fragile = false;

        for (Map.Entry<UUID, Integer> entry : shoppingCart.getProducts().entrySet()) {
            UUID productId = entry.getKey();
            int requestedQuantity = entry.getValue();

            WarehouseProduct product = warehouseProductRepository.findById(productId)
                    .orElseThrow(() -> new NoSpecifiedProductInWarehouseException("product not found " + productId));

            if (product.getQuantityAvailable() < requestedQuantity) {
                throw new ProductInShoppingCartLowQuantityInWarehouse("not enough product " + productId);
            }

            product.setQuantityAvailable(product.getQuantityAvailable() - requestedQuantity);
            warehouseProductRepository.save(product);

            QuantityState newState = determineState(product.getQuantityAvailable());
            shoppingStoreClient.setQuantity(
                    SetProductQuantityStateRequest.builder()
                            .productId(productId)
                            .quantityState(newState)
                            .build()
            );

            totalWeight += product.getWeight() * requestedQuantity;
            totalVolume += product.getDimension().getWidth()
                           * product.getDimension().getHeight()
                           * product.getDimension().getDepth() * requestedQuantity;

            fragile |= product.isFragile();
        }

        Booking booking = Booking.builder()
                .shoppingCartId(shoppingCart.getShoppingCartId())
                .products(shoppingCart.getProducts())
                .deliveryWeight(totalWeight)
                .deliveryVolume(totalVolume)
                .fragile(fragile)
                .build();

        bookingRepository.save(booking);

        return bookingMapper.toBookedProductDto(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public BookedProductsDto assemblyProductForOrderFromShoppingCart(AssemblyProductForOrderFromShoppingCartRequest request) {
        log.info("assemblyProductForOrderFromShoppingCart {} {}", request.getShoppingCartId(), request.getOrderId());

        Booking booking = bookingRepository.findById(request.getShoppingCartId())
                .orElseThrow(() -> new NoSpecifiedProductInWarehouseException("booking " + request.getShoppingCartId() + " not found"));

        if (booking.getProducts() == null || booking.getProducts().isEmpty()) {
            throw new NoSpecifiedProductInWarehouseException("cart not found");
        }

        return bookingMapper.toBookedProductDto(booking);
    }

    @Override
    @Transactional
    public void addProductQuantity(AddProductToWarehouseRequest request) {
        log.info("addProductQuantity {}", request);

        WarehouseProduct product = warehouseProductRepository.findById(request.getProductId())
                .orElseThrow(() -> new NoSpecifiedProductInWarehouseException("product not found"));

        product.setQuantityAvailable(product.getQuantityAvailable() + request.getQuantity());
        WarehouseProduct updatedProduct = warehouseProductRepository.save(product);

        QuantityState newState = determineState(updatedProduct.getQuantityAvailable());
        shoppingStoreClient.setQuantity(
                SetProductQuantityStateRequest.builder()
                        .productId(request.getProductId())
                        .quantityState(newState)
                        .build()
        );
    }

    @Override
    public AddressDto getWarehouseAddress() {
        return getDefaultAddress();
    }

    private QuantityState determineState(int quantity) {
        if (quantity == 0) {
            return QuantityState.ENDED;
        }
        if (quantity > 0 && quantity < LIMIT_COUNT) {
            return QuantityState.FEW;
        }
        if (quantity >= LIMIT_COUNT && quantity <= ENOUGH_COUNT) {
            return QuantityState.ENOUGH;
        }
        return QuantityState.MANY;
    }
}
