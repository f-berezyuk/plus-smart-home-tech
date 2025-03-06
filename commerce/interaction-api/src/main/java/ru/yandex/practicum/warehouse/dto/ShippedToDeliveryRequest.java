package ru.yandex.practicum.warehouse.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippedToDeliveryRequest {
    private UUID orderId;

    private UUID deliveryId;
}
