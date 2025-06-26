package com.riu.practice.palabrota.infrastructure.adapter.api.internal.mapper;

import com.riu.practice.palabrota.domain.model.AttemptResultModel;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.dto.ReturnAttemptDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface AttemptResultMapper {
    ReturnAttemptDto toDto(AttemptResultModel model);
    AttemptResultModel toModel(ReturnAttemptDto dto);
}
