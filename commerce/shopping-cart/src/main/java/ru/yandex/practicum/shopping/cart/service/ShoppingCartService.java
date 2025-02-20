package ru.yandex.practicum.shopping.cart.service;

import java.util.Map;
import java.util.UUID;

import ru.yandex.practicum.shopping.cart.dto.ChangeProductQuantityRequest;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;

public interface ShoppingCartService {

    ShoppingCartDto addProducts(String username, Map<UUID, Integer> products);

    ShoppingCartDto getShoppingCart(String username);

    ShoppingCartDto removeProducts(String username, Map<UUID, Integer> products);

    void deactivateShoppingCart(String username);

    ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest request);

    BookedProductsDto bookProducts(String username);
}
