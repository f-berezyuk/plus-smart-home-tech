package ru.yandex.practicum.shopping.store.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.practicum.shopping.store.dto.PageableDto;
import ru.yandex.practicum.shopping.store.dto.ProductDto;
import ru.yandex.practicum.shopping.store.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.shopping.store.enums.ProductCategory;
import ru.yandex.practicum.shopping.store.service.ShoppingStoreService;

@RestController
@RequestMapping("/api/v1/shopping-store")
@RequiredArgsConstructor
public class ShoppingStoreController {
    private final ShoppingStoreService shoppingStoreService;

    @GetMapping
    List<ProductDto> getProducts(@RequestParam("category") ProductCategory category,
                                 @Valid @RequestParam("pageableDto") PageableDto pageableDto) {
        return shoppingStoreService.getProductsByCategory(category, pageableDto);
    }

    @PutMapping
    ProductDto createNewProduct(@Valid @RequestBody ProductDto productDto) {
        return shoppingStoreService.createNewProduct(productDto);
    }

    @PostMapping
    ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return shoppingStoreService.updateProduct(productDto);
    }

    @PostMapping("/removeProductFromStore")
    boolean removeProductFromStore(@RequestParam("productId") UUID productId) {
        return shoppingStoreService.removeProductFromStore(productId);
    }

    @PostMapping("/quantityState")
    boolean setProductQuantityState(@Valid @RequestBody SetProductQuantityStateRequest request) {
        return shoppingStoreService.setProductQuantityState(request);
    }

    @GetMapping("/{productId}")
    ProductDto getProduct(@PathVariable("productId") UUID productId) {
        return shoppingStoreService.getProduct(productId);
    }

    @GetMapping("/allBy")
    Map<UUID, ProductDto> findAllByIds(@RequestParam("ids[]") Set<UUID> ids) {
        return shoppingStoreService.findAllByIds(ids);
    }
}
