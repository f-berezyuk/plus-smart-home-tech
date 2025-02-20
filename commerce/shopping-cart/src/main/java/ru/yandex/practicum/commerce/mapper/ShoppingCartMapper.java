package ru.yandex.practicum.commerce.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.commerce.entity.ShoppingCart;
import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShoppingCartMapper {
    @Mapping(target = "shoppingCartId", source = "shoppingCartId")
    ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart);
}
