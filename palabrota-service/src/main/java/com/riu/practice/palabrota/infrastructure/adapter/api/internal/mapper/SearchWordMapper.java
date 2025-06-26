package com.riu.practice.palabrota.infrastructure.adapter.api.internal.mapper;

import com.riu.practice.palabrota.domain.model.WordModel;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.dto.WordSearchDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface SearchWordMapper {
    WordSearchDto toDto(WordModel model);
    WordModel toModel(WordSearchDto dto);
}
