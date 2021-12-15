package com.edcm.backend.core.services;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;

public interface CommodityTransactionHandler {
    CommodityEntity createOrFindCommodity(String eddnName);

    CommodityEntity saveCommodity(CommodityEntity commodityEntity);
}
