package com.riu.practice.palabrota.infrastructure.adapter.api.external.mapper;

import com.riu.practice.palabrota.domain.model.MeaningsModel;
import com.riu.practice.palabrota.domain.model.WordModel;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeDataDto;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeMeaningsDto;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeSensesDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface RaeWordMapper {
    @Mapping(target = "origin", expression = "java(getOrigin(dto.meanings()))")
    @Mapping(target = "meanings", expression = "java(mapMeanings(dto.meanings()))")
    WordModel toModel(RaeDataDto dto);

    default String getOrigin(List<RaeMeaningsDto> meanings) {
        return (meanings != null && !meanings.isEmpty() && meanings.get(0).origin() != null)
                ? meanings.get(0).origin().raw()
                : null;
    }

    default List<MeaningsModel> mapMeanings(List<RaeMeaningsDto> meanings) {
        List<MeaningsModel> result = new ArrayList<>();
        if (meanings != null)
            for (RaeMeaningsDto meaning : meanings)
                if (meaning.senses() != null)
                    for (RaeSensesDto sense : meaning.senses())
                        result.add(mapSense(sense));

        return result;
    }

    default MeaningsModel mapSense(RaeSensesDto sense) {
        return new MeaningsModel(
                sense.description(), // definitions
                sense.category(), // category
                sense.synonyms(), // synonyms
                sense.antonyms()  // antonyms
        );
    }
}
