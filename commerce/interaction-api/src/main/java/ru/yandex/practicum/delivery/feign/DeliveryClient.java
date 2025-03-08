package ru.yandex.practicum.delivery.feign;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.yandex.practicum.delivery.dto.DeliveryDto;
import ru.yandex.practicum.order.dto.OrderDto;

@FeignClient(name = "delivery-service", path = "/api/v1/delivery")
public interface DeliveryClient {

    @PutMapping
    DeliveryDto planDelivery(@RequestBody DeliveryDto deliveryDto);

    @PostMapping("/cost")
    BigDecimal deliveryCost(@RequestBody OrderDto orderDto);
}
