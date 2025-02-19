package ru.yandex.practicum.shopping.warehouse.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssemblyProductForOrderFromShoppingCartRequest {
    @NotNull(message = "shoppingCartId must be not empty")
    private UUID shoppingCartId;

    @NotNull(message = "orderId must be not empty")
    private UUID orderId;
}
