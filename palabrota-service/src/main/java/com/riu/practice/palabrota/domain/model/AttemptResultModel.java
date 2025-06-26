package com.riu.practice.palabrota.domain.model;

import java.util.List;
import java.util.UUID;

public record AttemptResultModel(
        UUID uuid,
        String word,
        int attemptsLeft,
        List<LetterModel> result
) {}
