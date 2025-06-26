package com.riu.practice.palabrota.domain.model;

import java.util.List;

public record MeaningsModel(String definition, String category, List<String> synonyms, List<String> antonyms) {
}
