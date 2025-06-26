package com.riu.practice.palabrota.infrastructure.adapter.api.external.dto;

import java.util.List;

public record RaeSensesDto(String description, String category, List<String> synonyms, List<String> antonyms) {
}
