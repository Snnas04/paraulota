package com.riu.practice.palabrota.infrastructure.adapter.api.internal.mapper;

import com.riu.practice.palabrota.domain.model.GameLevelsModel;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.dto.NewGameRequestDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface GameLevelsMapper {
    NewGameRequestDto toDto(GameLevelsModel model);
    GameLevelsModel toModel(NewGameRequestDto dto);
}
