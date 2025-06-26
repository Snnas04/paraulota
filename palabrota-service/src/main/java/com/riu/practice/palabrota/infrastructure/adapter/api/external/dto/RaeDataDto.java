package com.riu.practice.palabrota.infrastructure.adapter.api.external.dto;

import java.util.List;

public record RaeDataDto(String word, List<RaeMeaningsDto> meanings) {
}
