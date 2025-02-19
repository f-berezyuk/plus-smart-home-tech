package ru.yandex.practicum.shopping.cart.feign;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;

@FeignClient(name = "shopping-cart-service", path = "/api/v1/shopping-cart")
public interface ShoppingCartClient {

    @GetMapping("/{id}")
    ShoppingCartDto getShoppingCartById(@PathVariable UUID id);
}
