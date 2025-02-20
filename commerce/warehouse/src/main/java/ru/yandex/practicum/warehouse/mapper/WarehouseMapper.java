package ru.yandex.practicum.warehouse.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.warehouse.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.warehouse.entity.WarehouseProduct;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {DimensionMapper.class}
)
public interface WarehouseMapper {
    WarehouseProduct toWarehouseProduct(NewProductInWarehouseRequest request);
}
