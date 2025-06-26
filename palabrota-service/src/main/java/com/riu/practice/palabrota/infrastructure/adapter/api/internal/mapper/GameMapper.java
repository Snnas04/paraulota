package com.riu.practice.palabrota.infrastructure.adapter.api.internal.mapper;

import com.riu.practice.palabrota.domain.model.GameModel;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.dto.NewGameResponseDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface GameMapper {
    NewGameResponseDto toDto(GameModel model);
    GameModel toModel(NewGameResponseDto dto);
}
