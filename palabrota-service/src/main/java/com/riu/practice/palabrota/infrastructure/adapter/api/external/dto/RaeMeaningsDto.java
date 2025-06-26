package com.riu.practice.palabrota.infrastructure.adapter.api.external.dto;

import java.util.List;

public record RaeMeaningsDto(RaeOriginDto origin, List<RaeSensesDto> senses) {
}
