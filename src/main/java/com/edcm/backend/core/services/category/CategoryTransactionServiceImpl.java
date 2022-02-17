package com.edcm.backend.core.services.category;

import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryTransactionServiceImpl implements CategoryTransactionService {
    private final CommodityCategoryRepository repository;

    @Override
    public CommodityCategory createOrFindCategory(String category) {
        return repository.findCommodityCategoryEntityByName(category)
            .orElseGet(() -> {
                var categoryEntity = new CommodityCategory();
                categoryEntity.setName(category);
                return repository.save(categoryEntity);
            });
    }
}
