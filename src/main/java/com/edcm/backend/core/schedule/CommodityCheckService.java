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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service("commodityCheckService")
public class CommodityCheckService {
    private final GithubDataProvider dataProvider;
    private final CommodityTransactionHandler commodityTransactionHandler;
    private final CategoryTransactionHandler categoryTransactionHandler;
    private final CommodityRepository repository;
    private final CommodityCategoryRepository categoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "${values.scheduled.github.cron}")
    public void updateCommodities() {
        log.debug("Checking commodities updates");

        var commodities = dataProvider.getCommodities()
            .stream()
            .map(commodity -> {
                if (!repository.existsByEddnName(commodity.getEddnName())) {
                    CommodityCategoryEntity category = categoryTransactionHandler
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
