package com.riu.practice.palabrota.infrastructure.adapter.db;

import com.riu.practice.palabrota.domain.model.GameModel;
import com.riu.practice.palabrota.domain.port.repository.GameResponseRepositoryPort;
import com.riu.practice.palabrota.infrastructure.adapter.db.entity.GameEntity;
import com.riu.practice.palabrota.infrastructure.adapter.db.mapper.GameRepositoryMapperDb;
import com.riu.practice.palabrota.infrastructure.adapter.db.repository.GameRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class GameRepositoryDb implements GameResponseRepositoryPort {
    private final GameRepositoryJpa repository;
    private final GameRepositoryMapperDb mapper;

    @Override
    public UUID save(GameModel model) {
        GameEntity entity = mapper.toEntity(model);
        repository.save(entity);

        return entity.getId();
    }

    @Override
    public GameModel findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Game not found with id: " + id));
    }
}
