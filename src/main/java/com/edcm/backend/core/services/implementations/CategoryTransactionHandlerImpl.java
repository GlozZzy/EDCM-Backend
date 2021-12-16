package com.edcm.backend.core.services.implementations;

import com.edcm.backend.core.services.CategoryTransactionHandler;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CategoryTransactionHandlerImpl implements CategoryTransactionHandler {
    private final CommodityCategoryRepository repository;

    @Override
    public CommodityCategoryEntity createOrFindCategory(String category) {
        return repository.findCommodityCategoryEntityByName(category)
            .orElseGet(() -> {
                var categoryEntity = new CommodityCategoryEntity();
                categoryEntity.setName(category);
                return repository.saveAndFlush(categoryEntity);
            });
    }
}
