package ru.yandex.practicum.shopping.cart.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeProductQuantityRequest {
    @NotNull(message = "Product id must be not empty")
    private UUID productId;

    @Min(value = 0, message = "newQuantity must greater then 0")
    private int newQuantity;
}
