package com.edcm.backend.core.schedule;

import com.edcm.backend.core.services.category.CategoryTransactionService;
import com.edcm.backend.core.services.commodity.CommodityTransactionService;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategory;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityCategoryRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service("commodityCheckService")
public class CommodityCheckService {
    private final GithubDataProvider dataProvider;
    private final CommodityTransactionService commodityTransactionService;
    private final CategoryTransactionService categoryTransactionService;
    private final CommodityRepository repository;
    private final CommodityCategoryRepository categoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${scheduled.github.cron}")
    public void updateCommodities() {
        log.debug("Checking commodities updates");

        var commodities = dataProvider.getCommodities()
            .stream()
            .map(commodity -> {
                if (!repository.existsByEddnName(commodity.getEddnName())) {
                    CommodityCategory category = categoryTransactionService
                        .createOrFindCategory(commodity.getCategory().getName());
                    return new  CommodityEntity(
                        commodity.getName(),
                        commodity.getEddnName(),
                        category);
                }
                return null;
            })
            .filter(Objects::nonNull)
            .toList();
        repository.saveAll(commodities);
    }
}
