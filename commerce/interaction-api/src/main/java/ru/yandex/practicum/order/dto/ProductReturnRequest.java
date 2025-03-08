package ru.yandex.practicum.order.dto;

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
public class ProductReturnRequest {
    private UUID orderId;
    private Map<UUID, Integer> products;
}
