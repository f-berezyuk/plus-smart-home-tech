package ru.yandex.practicum.warehouse.feign;

import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.common.dto.AddressDto;
import ru.yandex.practicum.common.exception.ServerUnavailableException;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.warehouse.dto.AssemblyProductForOrderFromShoppingCartRequest;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;
import ru.yandex.practicum.warehouse.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.warehouse.dto.ShippedToDeliveryRequest;

@Component
public class WarehouseFallback implements WarehouseClient {
    @Override
    public BookedProductsDto bookProducts(ShoppingCartDto shoppingCart) {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }

    @Override
    public BookedProductsDto assembleOrder(AssemblyProductForOrderFromShoppingCartRequest request) {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }

    @Override
    public NewProductInWarehouseRequest saveProduct(NewProductInWarehouseRequest newProduct) {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }

    @Override
    public void returnProducts(Map<String, Integer> products) {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }

    @Override
    public AddProductToWarehouseRequest addProduct(AddProductToWarehouseRequest addProduct) {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }

    @Override
    public AddressDto getAddress() {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }

    @Override
    public void shippedToDelivery(ShippedToDeliveryRequest request) {
        throw new ServerUnavailableException("Endpoint /api/v1/warehouse method PUT is unavailable");
    }
}
