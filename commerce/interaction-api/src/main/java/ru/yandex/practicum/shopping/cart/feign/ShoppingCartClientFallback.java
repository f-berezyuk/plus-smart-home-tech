package ru.yandex.practicum.shopping.cart.feign;

import java.util.List;
import java.util.Map;

import jakarta.servlet.UnavailableException;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.shopping.cart.dto.ChangeProductQuantityRequest;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;

@Component
public class ShoppingCartClientFallback implements ShoppingCartClient {
    @Override
    public ShoppingCartDto findShoppingCart(String username, String shoppingCartId) throws UnavailableException {
        throw new UnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public ShoppingCartDto saveShoppingCart(String username, Map<String, Integer> products) throws UnavailableException {
        throw new UnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public void deleteShoppingCart(String username) throws UnavailableException {
        throw new UnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public ShoppingCartDto updateShoppingCart(String username, List<String> products) throws UnavailableException {
        throw new UnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest quantity) throws UnavailableException {
        throw new UnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }

    @Override
    public BookedProductsDto bookingProducts(String username) throws UnavailableException {
        throw new UnavailableException("Fallback response. Service Shopping cart is Unavailable.");
    }
}
