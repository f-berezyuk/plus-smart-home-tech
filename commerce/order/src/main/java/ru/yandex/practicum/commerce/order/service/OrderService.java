package ru.yandex.practicum.commerce.order.service;

import java.util.List;
import java.util.UUID;

import ru.yandex.practicum.order.dto.CreateNewOrderRequest;
import ru.yandex.practicum.order.dto.OrderDto;
import ru.yandex.practicum.order.dto.ProductReturnRequest;

public interface OrderService {
    List<OrderDto> getClientOrders(String username);

    OrderDto createNewOrder(CreateNewOrderRequest createNewOrderRequest);

    OrderDto payment(UUID orderId);

    OrderDto paymentFailed(UUID orderId);

    OrderDto delivery(UUID orderId);

    OrderDto deliveryFailed(UUID orderId);

    OrderDto productReturn(ProductReturnRequest productReturnRequest);

    OrderDto completed(UUID orderId);

    OrderDto calculateTotalCost(UUID orderId);

    OrderDto calculateDeliveryCost(UUID orderId);

    OrderDto assembly(UUID orderId);

    OrderDto assemblyFailed(UUID orderId);
}
