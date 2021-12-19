package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> {

    CommodityEntity getCommodityEntityByName(String name);

    CommodityEntity getCommodityEntitiesById(Long id);

    CommodityEntity getCommodityEntitiesByEddnName(String eddnName);

    Optional<CommodityEntity> findCommodityEntityByEddnName(String eddnName);

    List<CommodityEntity> getCommodityEntitiesByCategory(CommodityCategoryEntity category);

    List<CommodityEntity> findDistinctByEddnNameInIgnoreCase(@NonNull Collection<String> eddnNames);



    boolean existsByName(String name);

    boolean existsByEddnName(String eddnName);
}
