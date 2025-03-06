package ru.yandex.practicum.commerce.delivery.service;

import java.math.BigDecimal;
import java.util.UUID;

import ru.yandex.practicum.delivery.dto.DeliveryDto;
import ru.yandex.practicum.order.dto.OrderDto;

public interface DeliveryService {
    DeliveryDto planDelivery(DeliveryDto deliveryDto);

    BigDecimal deliveryCost(OrderDto orderDto);

    void deliverySuccessful(UUID orderId);

    void deliveryFailed(UUID orderId);

    void deliveryPicked(UUID deliveryId);
}
