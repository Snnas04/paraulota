package com.riu.practice.palabrota.infrastructure.adapter.api.internal.mapper;

import com.riu.practice.palabrota.domain.model.DoAttemptModel;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.dto.DoAttemptDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface DoAttemptMapper {
    DoAttemptDto toDto(DoAttemptModel model);
    DoAttemptModel toModel(DoAttemptDto dto);
}
