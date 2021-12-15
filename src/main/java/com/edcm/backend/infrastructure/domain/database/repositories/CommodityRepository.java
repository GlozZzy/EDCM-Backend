package com.edcm.backend.infrastructure.domain.database.repositories;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.Name;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> {

    CommodityEntity getCommodityEntityByName(String name);

    CommodityEntity getCommodityEntitiesById(Long id);

    CommodityEntity getCommodityEntitiesByEddnName(String eddnName);

    Optional<CommodityEntity> findCommodityEntityByEddnName(String eddnName);

    List<CommodityEntity> getCommodityEntitiesByCategory(CommodityCategoryEntity category);

    boolean existsByName(String name);

    boolean existsByEddnName(String eddnName);
}
