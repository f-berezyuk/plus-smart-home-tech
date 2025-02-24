package ru.yandex.practicum.warehouse.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ru.yandex.practicum.warehouse.dto.DimensionDto;
import ru.yandex.practicum.warehouse.entity.Dimension;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface DimensionMapper {
    Dimension toDimension(DimensionDto dto);
}
