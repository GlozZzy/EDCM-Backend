package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProhibitedCommodityRepository extends JpaRepository<ProhibitedCommodityEntity, Long> {

}
