package com.riu.practice.palabrota.domain.model;

import java.util.List;

public record WordModel(String word, String origin, List<MeaningsModel> meanings) {
}
