package com.edcm.backend.core.services;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;

public interface CategoryTransactionHandler {
    CommodityCategoryEntity createOrFindCategory(String category);
}
