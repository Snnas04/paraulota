package com.riu.practice.palabrota.domain.port.repository;

import com.riu.practice.palabrota.domain.model.GameModel;

import java.util.UUID;

public interface GameResponseRepositoryPort {
    UUID save(GameModel model);
    GameModel findById(UUID id);
}
