package com.edcm.backend.core.services;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;
import org.springframework.data.jpa.repository.Lock;

public interface CategoryTransactionHandler {
    CommodityCategoryEntity createOrFindCategory(String category);
}
