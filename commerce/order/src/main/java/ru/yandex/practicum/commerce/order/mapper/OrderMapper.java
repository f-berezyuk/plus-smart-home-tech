package ru.yandex.practicum.commerce.order.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.commerce.order.entity.Order;
import ru.yandex.practicum.order.dto.OrderDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
}
