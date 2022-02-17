package com.edcm.backend.core.services.commodity;

import com.edcm.backend.core.services.category.CategoryTransactionService;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class CommodityTransactionServiceImpl implements CommodityTransactionService {
    private final CommodityRepository repository;
    private final CategoryTransactionService categoryTransactionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CommodityEntity createOrFindCommodity(String eddnName) {
        if (repository.existsByEddnName(eddnName)) {
            return repository.getCommodityEntitiesByEddnName(eddnName);
        } else {
            var category = categoryTransactionService.createOrFindCategory("Unknown");
            return repository.saveAndFlush(new CommodityEntity(eddnName, eddnName, category));
        }
    }

    @Override
    public CommodityEntity saveCommodity (CommodityEntity commodityEntity) {
        return repository.save(commodityEntity);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)

    public List<CommodityEntity> findAllByEddnName(Collection<String> commodityNames) {
        return repository.findDistinctByEddnNameInIgnoreCase(commodityNames);
    }

    @Override
    public boolean isExistByEddnName(String eddnName) {
        return repository.existsByEddnName(eddnName);
    }
}
