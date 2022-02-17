package com.edcm.backend.core.services.category;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;

public interface CategoryTransactionService {
    CommodityCategory createOrFindCategory(String category);
}
