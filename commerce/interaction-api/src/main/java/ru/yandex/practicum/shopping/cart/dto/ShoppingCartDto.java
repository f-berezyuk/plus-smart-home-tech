package ru.yandex.practicum.shopping.cart.dto;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartDto {

    private UUID shoppingCartId;

    private Map<UUID, Integer> products;
}
