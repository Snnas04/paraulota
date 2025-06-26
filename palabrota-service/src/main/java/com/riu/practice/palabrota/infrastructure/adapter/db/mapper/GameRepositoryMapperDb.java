package com.riu.practice.palabrota.infrastructure.adapter.db.mapper;

import com.riu.practice.palabrota.domain.model.GameModel;
import com.riu.practice.palabrota.infrastructure.adapter.db.entity.GameEntity;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface GameRepositoryMapperDb {
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "attempts", source = "gameSettings.attempts")
    GameEntity toEntity(GameModel model);

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "gameSettings.attempts", source = "attempts")
    @Mapping(target = "gameSettings.letters", ignore = true)
    GameModel toModel(GameEntity entity);
}
