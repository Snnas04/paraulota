package com.riu.practice.palabrota.infrastructure.adapter.db.repository;

import com.riu.practice.palabrota.infrastructure.adapter.db.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepositoryJpa extends JpaRepository<GameEntity, UUID> {
}
