package ru.yandex.practicum.warehouse.dto;

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
public class AddProductToWarehouseRequest {
    @NotNull(message = "id must be not empty")
    private UUID productId;

    @Min(value = 1, message = "quantity must be greater 0")
    private int quantity;
}
