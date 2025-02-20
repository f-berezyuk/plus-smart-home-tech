package ru.yandex.practicum.shopping.store.service;

import java.util.List;
import java.util.UUID;

import ru.yandex.practicum.shopping.store.dto.PageableDto;
import ru.yandex.practicum.shopping.store.dto.ProductDto;
import ru.yandex.practicum.shopping.store.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.shopping.store.enums.ProductCategory;

public interface ShoppingStoreService {

    ProductDto createNewProduct(ProductDto productDto);

    boolean removeProductFromStore(UUID productId);

    List<ProductDto> getProductsByCategory(ProductCategory category, PageableDto pageableDto);

    ProductDto updateProduct(ProductDto productDto);

    boolean setProductQuantityState(SetProductQuantityStateRequest request);

    ProductDto getProduct(UUID productId);
}
