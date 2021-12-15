package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.StationEconomyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationEconomyRepository extends JpaRepository<StationEconomyEntity, Long> {

}
