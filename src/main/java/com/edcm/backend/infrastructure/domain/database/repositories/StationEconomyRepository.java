package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.StationEconomy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationEconomyRepository extends JpaRepository<StationEconomy, Long> {

}
