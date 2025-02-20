package ru.yandex.practicum.shopping.cart.feign;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.yandex.practicum.common.exception.ServerUnavailableException;
import ru.yandex.practicum.shopping.cart.dto.ChangeProductQuantityRequest;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;

@Component
public class ShoppingCartClientFallback implements ShoppingCartClient {
    @Override
    public ShoppingCartDto findShoppingCart(String username, String shoppingCartId) {
        throw new ServerUnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public ShoppingCartDto saveShoppingCart(String username, Map<String, Integer> products) {
        throw new ServerUnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public void deleteShoppingCart(String username) {
        throw new ServerUnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public ShoppingCartDto updateShoppingCart(String username, List<String> products) {
        throw new ServerUnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest quantity) {
        throw new ServerUnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public BookedProductsDto bookingProducts(String username) {
        throw new ServerUnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }
}
