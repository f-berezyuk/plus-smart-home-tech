package ru.yandex.practicum.shopping.warehouse.dto;

import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewProductInWarehouseRequest {
    @NotNull(message = "id must be not empty")
    private UUID productId;

    @NotNull(message = "fragile must be not empty")
    private boolean fragile;

    @NotNull(message = "dimension must be not empty")
    private DimensionDto dimension;

    @DecimalMin(value = "1.0", message = "weight must be greater 0")
    private double weight;
}
