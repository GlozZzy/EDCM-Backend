package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface StationRepository extends JpaRepository<StationEntity, Long> {

    boolean existsByName(@NonNull String name);

    StationEntity getByName(@NonNull String name);

    Optional<StationEntity> findByName(@NonNull String name);

    Optional<StationEntity> findByNameAndSystem_Name(@NonNull String stationName, @NonNull String systemName);

}
