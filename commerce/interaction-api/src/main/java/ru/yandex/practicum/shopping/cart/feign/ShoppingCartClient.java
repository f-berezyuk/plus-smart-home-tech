package ru.yandex.practicum.shopping.cart.feign;

import java.util.List;
import java.util.Map;

import jakarta.servlet.UnavailableException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ru.yandex.practicum.shopping.cart.dto.ChangeProductQuantityRequest;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;

@FeignClient(name = "shopping-cart-service",
        path = "/api/v1/shopping-cart",
        fallback = ShoppingCartClientFallback.class)
public interface ShoppingCartClient {
    @GetMapping("/api/v1/shopping-cart")
    ShoppingCartDto findShoppingCart(@RequestParam(required = false) String username,
                                     @RequestParam(required = false) String shoppingCartId) throws UnavailableException;

    @PutMapping("/api/v1/shopping-cart")
    ShoppingCartDto saveShoppingCart(@RequestParam String username,
                                     @RequestBody Map<String, Integer> products) throws UnavailableException;

    @DeleteMapping("/api/v1/shopping-cart")
    void deleteShoppingCart(@RequestParam String username) throws UnavailableException;

    @PostMapping("/api/v1/shopping-cart/remove")
    ShoppingCartDto updateShoppingCart(@RequestParam String username,
                                       @RequestBody List<String> products) throws UnavailableException;

    @PostMapping("/api/v1/shopping-cart/change-quantity")
    ShoppingCartDto changeProductQuantity(@RequestParam String username,
                                          @RequestBody ChangeProductQuantityRequest quantity) throws UnavailableException;

    @PostMapping("/api/v1/shopping-cart/booking")
    BookedProductsDto bookingProducts(@RequestParam String username) throws UnavailableException;
}
