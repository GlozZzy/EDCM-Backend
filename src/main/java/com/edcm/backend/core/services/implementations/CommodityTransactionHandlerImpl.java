package com.edcm.backend.core.services.implementations;

import com.edcm.backend.core.services.CategoryTransactionHandler;
import com.edcm.backend.core.services.CommodityTransactionHandler;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommodityTransactionHandlerImpl implements CommodityTransactionHandler {
    private final CommodityRepository repository;
    private final CategoryTransactionHandler categoryTransactionHandler;

    @Override
    public CommodityEntity createOrFindCommodity(String eddnName) {
        return repository
            .findCommodityEntityByEddnName(eddnName)
            .orElseGet(() -> {
                var category = categoryTransactionHandler.createOrFindCategory("Unknown");
                return repository.saveAndFlush(new CommodityEntity(eddnName, eddnName, category));
            });
    }

    @Override
    public CommodityEntity saveCommodity(CommodityEntity commodityEntity) {
        return repository.save(commodityEntity);
    }


}
