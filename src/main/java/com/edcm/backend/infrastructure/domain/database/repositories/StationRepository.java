package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationRepository extends JpaRepository<StationEntity, Long> {

    boolean existsByName(String name);

    StationEntity getByName(String name);

    Optional<StationEntity> findByName(String name);
}
