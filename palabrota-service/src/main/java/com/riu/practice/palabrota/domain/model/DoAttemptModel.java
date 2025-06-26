package com.riu.practice.palabrota.domain.model;

import java.util.UUID;

public record DoAttemptModel(
        UUID uuid,
        String word,
        Integer attempt
) {}
