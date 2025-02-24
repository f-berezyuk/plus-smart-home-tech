package ru.yandex.practicum.shopping.cart.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.shopping.cart.dto.ShoppingCartDto;
import ru.yandex.practicum.shopping.cart.entity.ShoppingCart;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShoppingCartMapper {
    ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart);
}
