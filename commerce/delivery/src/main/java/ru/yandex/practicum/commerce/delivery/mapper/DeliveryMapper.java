package ru.yandex.practicum.commerce.delivery.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.commerce.delivery.entity.Delivery;
import ru.yandex.practicum.delivery.dto.DeliveryDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DeliveryMapper {

    @Mapping(source = "fromAddress", target = "fromAddress")
    @Mapping(source = "toAddress", target = "toAddress")
    DeliveryDto toDeliveryDto(final Delivery delivery);

    @Mapping(target = "fragile", ignore = true)
    @Mapping(target = "deliveryWeight", ignore = true)
    @Mapping(target = "deliveryVolume", ignore = true)
    @Mapping(source = "fromAddress", target = "fromAddress")
    @Mapping(source = "toAddress", target = "toAddress")
    Delivery fromDeliveryDto(final DeliveryDto deliveryDto);
}
