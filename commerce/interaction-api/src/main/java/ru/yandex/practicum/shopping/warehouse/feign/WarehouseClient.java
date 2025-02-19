package ru.yandex.practicum.shopping.warehouse.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.shopping.warehouse.dto.AssemblyProductForOrderFromShoppingCartRequest;
import ru.yandex.practicum.shopping.warehouse.dto.BookedProductsDto;

@FeignClient(name = "warehouse-service", path = "/api/v1/warehouse")
public interface WarehouseClient {

    @PostMapping("/booking")
    BookedProductsDto bookProducts(@RequestBody ShoppingCartDto shoppingCart);

    @PostMapping("/assembly")
    BookedProductsDto assembleOrder(@RequestBody AssemblyProductForOrderFromShoppingCartRequest request);
}
