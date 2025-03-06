package ru.yandex.practicum.warehouse.controller;

import java.util.Map;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.common.dto.AddressDto;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.warehouse.dto.AssemblyProductForOrderFromShoppingCartRequest;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;
import ru.yandex.practicum.warehouse.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.warehouse.service.WarehouseServiceImpl;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseServiceImpl warehouseService;

    @PutMapping
    public void addNewProduct(@RequestBody @Valid NewProductInWarehouseRequest request) {
        warehouseService.addNewProduct(request);
    }

    @PostMapping("/return")
    public void acceptReturn(@RequestBody Map<UUID, Integer> products) {
        warehouseService.acceptReturn(products);
    }

    @PostMapping("/booking")
    public BookedProductsDto bookProductForShoppingCart(@RequestBody @Valid ShoppingCartDto cartDto) {
        return warehouseService.bookProductForShoppingCart(cartDto);
    }

    @PostMapping("/assembly")
    public BookedProductsDto assemblyProductForOrderFromShoppingCart(
            @RequestBody @Valid AssemblyProductForOrderFromShoppingCartRequest request) {
        return warehouseService.assemblyProductForOrderFromShoppingCart(request);
    }

    @PostMapping("/add")
    public void addProductQuantity(@RequestBody @Valid AddProductToWarehouseRequest request) {
        warehouseService.addProductQuantity(request);
    }

    @GetMapping("/address")
    public AddressDto getWarehouseAddress() {
        return warehouseService.getWarehouseAddress();
    }
}
