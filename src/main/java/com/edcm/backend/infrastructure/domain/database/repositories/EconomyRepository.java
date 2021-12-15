package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.EconomyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EconomyRepository extends JpaRepository<EconomyEntity, Long> {
    Optional<EconomyEntity> findByName(String name);
}
