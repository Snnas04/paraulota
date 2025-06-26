package com.riu.practice.palabrota.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record GameModel(
        UUID uuid,
        String word,
        @JsonProperty("game-settings")
        GameSettingsModel gameSettings
) {}
