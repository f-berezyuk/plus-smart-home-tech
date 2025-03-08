package ru.yandex.practicum.commerce.payment.service;

import java.math.BigDecimal;
import java.util.UUID;

import ru.yandex.practicum.order.dto.OrderDto;
import ru.yandex.practicum.payment.dto.PaymentDto;

public interface PaymentService {
    PaymentDto payment(OrderDto orderDto);

    BigDecimal productCost(OrderDto orderDto);

    BigDecimal getTotalCost(OrderDto orderDto);

    void paymentSuccess(UUID paymentId);

    void paymentFailed(UUID paymentId);
}
