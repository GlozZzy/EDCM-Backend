package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommodityCategoryRepository extends JpaRepository<CommodityCategoryEntity, Long> {

    @NonNull
    CommodityCategoryEntity getCommodityCategoryEntityById(Long id);

    CommodityCategoryEntity getCommodityCategoryEntitiesByName(String name);

    Optional<CommodityCategoryEntity> findCommodityCategoryEntityByName(String name);

    boolean existsCommodityCategoryEntityByName(String name);


}
