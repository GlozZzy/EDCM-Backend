package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemRepository extends JpaRepository<SystemEntity, Long>{

    Optional<SystemEntity> findByName(String name);
}