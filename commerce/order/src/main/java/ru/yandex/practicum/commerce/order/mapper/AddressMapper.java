package ru.yandex.practicum.commerce.order.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.commerce.order.entity.Address;
import ru.yandex.practicum.common.dto.AddressDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {

    @Mapping(target = "addressId", ignore = true)
    Address fromAddressDto(AddressDto addressDto);

    AddressDto toAddressDto(Address address);
}
