package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.ProhibitedCommodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProhibitedCommodityRepository extends JpaRepository<ProhibitedCommodity, Long> {

}
