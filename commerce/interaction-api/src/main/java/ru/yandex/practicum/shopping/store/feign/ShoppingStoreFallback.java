package ru.yandex.practicum.shopping.store.feign;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.common.exception.ServerUnavailableException;
import ru.yandex.practicum.shopping.store.dto.PageableDto;
import ru.yandex.practicum.shopping.store.dto.ProductDto;
import ru.yandex.practicum.shopping.store.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.shopping.store.enums.ProductCategory;

@Component
public class ShoppingStoreFallback implements ShoppingStoreClient {
    @Override
    public PageableDto findAllProducts(ProductCategory category, Integer page, Integer size, List<String> sort,
                                       String sortOrder) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store method GET is unavailable");
    }

    @Override
    public Map<UUID, ProductDto> findAllByIds(Set<UUID> productIds) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store/allBy method POST is unavailable");
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store method GET is unavailable");
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store method GET is unavailable");
    }

    @Override
    public Boolean deleteProduct(String productId) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store method GET is unavailable");
    }

    @Override
    public Boolean setQuantity(SetProductQuantityStateRequest request) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store method GET is unavailable");
    }

    @Override
    public ProductDto findProductById(String productId) {
        throw new ServerUnavailableException("Endpoint /api/v1/shopping-store method GET is unavailable");
    }
}
