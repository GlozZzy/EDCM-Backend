package com.edcm.backend.core.schedule;

import com.edcm.backend.core.services.CategoryTransactionHandler;
import com.edcm.backend.core.services.CommodityTransactionHandler;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityCategoryEntity;
import com.edcm.backend.infrastructure.domain.database.entities.CommodityEntity;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityCategoryRepository;
import com.edcm.backend.infrastructure.domain.database.repositories.CommodityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("commodityCheckService")
public class CommodityCheckService {
    private final GithubDataProvider dataProvider;
    private final CommodityTransactionHandler commodityTransactionHandler;
    private final CategoryTransactionHandler categoryTransactionHandler;
    private final CommodityRepository repository;
    private final CommodityCategoryRepository categoryRepository;

    @Transactional
    @Scheduled(cron = "${values.scheduled.github.cron}")
    public void updateCommodities() {
        log.info("Checking commodities updates");

        dataProvider.getCommodities()
            .forEach(commodity -> {
                if (!repository.existsByEddnName(commodity.getEddnName())) {
                    CommodityCategoryEntity category = categoryTransactionHandler
                        .createOrFindCategory(commodity.getCategory().getName());
                    CommodityEntity entity = new CommodityEntity(
                        commodity.getName(),
                        commodity.getEddnName(),
                        category);
                    repository.save(entity);
                }
            });
    }
}
