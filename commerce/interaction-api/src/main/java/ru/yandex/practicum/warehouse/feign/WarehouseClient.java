package ru.yandex.practicum.warehouse.feign;

import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.warehouse.dto.AddressDto;
import ru.yandex.practicum.warehouse.dto.AssemblyProductForOrderFromShoppingCartRequest;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;
import ru.yandex.practicum.warehouse.dto.NewProductInWarehouseRequest;

@FeignClient(name = "warehouse-service", path = "/api/v1/warehouse", fallback = WarehouseFallback.class)
public interface WarehouseClient {

    @PostMapping("/booking")
    BookedProductsDto bookProducts(@RequestBody ShoppingCartDto shoppingCart);

    @PostMapping("/assembly")
    BookedProductsDto assembleOrder(@RequestBody AssemblyProductForOrderFromShoppingCartRequest request);

    @PutMapping()
    NewProductInWarehouseRequest saveProduct(@RequestBody @Valid NewProductInWarehouseRequest newProduct);

    @PostMapping("/return")
    void returnProducts(@RequestBody Map<String, Integer> products);

    @PostMapping("/add")
    AddProductToWarehouseRequest addProduct(@RequestBody @Valid AddProductToWarehouseRequest addProduct);

    @GetMapping("/address")
    AddressDto getAddress();
}
