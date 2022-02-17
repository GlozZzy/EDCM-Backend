package com.edcm.backend.core.services.commodity;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;

import java.util.Collection;
import java.util.List;

public interface CommodityTransactionService {
    CommodityEntity createOrFindCommodity(String eddnName);

    CommodityEntity saveCommodity(CommodityEntity commodityEntity);

    List<CommodityEntity> findAllByEddnName(Collection<String> commodityNames);

    boolean isExistByEddnName(String eddnName);
}
