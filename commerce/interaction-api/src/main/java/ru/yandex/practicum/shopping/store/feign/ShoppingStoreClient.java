package ru.yandex.practicum.shopping.store.feign;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ru.yandex.practicum.shopping.store.dto.PageableDto;
import ru.yandex.practicum.shopping.store.dto.ProductDto;
import ru.yandex.practicum.shopping.store.dto.SetProductQuantityStateRequest;
import ru.yandex.practicum.shopping.store.enums.ProductCategory;

@FeignClient(name = "shopping-store", path = "/api/v1/shopping-store", fallback = ShoppingStoreFallback.class)
public interface ShoppingStoreClient {
    @GetMapping()
    PageableDto findAllProducts(@RequestParam ProductCategory category,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "1") Integer size,
                                @RequestParam(required = false) List<String> sort,
                                @RequestParam(defaultValue = "ASC") String sortOrder);

    @PostMapping("/allBy")
    Map<UUID, ProductDto> findAllByIds(@RequestParam Set<UUID> ids);

    @PostMapping()
    ProductDto updateProduct(@RequestBody @Valid ProductDto productDto);

    @PutMapping()
    ProductDto saveProduct(@RequestBody @Valid ProductDto productDto);

    @PostMapping("/removeProductFromStore")
    Boolean deleteProduct(@RequestBody String productId);

    @PostMapping("/quantityState")
    Boolean setQuantity(@RequestBody SetProductQuantityStateRequest request);

    @GetMapping("/{productId}")
    ProductDto findProductById(@PathVariable String productId);
}
