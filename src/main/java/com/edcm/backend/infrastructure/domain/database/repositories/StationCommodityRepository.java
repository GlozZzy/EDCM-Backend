package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.StationCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationCommodityRepository extends JpaRepository<StationCommodityEntity, Long> {
}