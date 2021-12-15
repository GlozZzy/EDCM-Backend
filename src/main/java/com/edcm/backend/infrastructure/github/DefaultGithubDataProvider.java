package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.mappers.CommoditiesDataMapper;
import com.edcm.backend.core.mappers.GithubCommodityMapper;
import com.edcm.backend.core.shared.data.CommoditySharedData;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItem;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItemWithEddn;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultGithubDataProvider implements GithubDataProvider {
    private final GithubOperations githubOperations;
    private final GithubCommodityMapper mapper;

    @Override
    public List<CommoditySharedData> getCommodities() {
        return githubOperations.getCommodities()
            .getItems()
            .entrySet()
            .stream()
            .map(item -> new GithubCommodityItemWithEddn(
                item.getValue().getId(),
                item.getValue().getCategory(),
                item.getValue().getName(),
                item.getKey()))
            .map(mapper::toSharedData)
            .collect(Collectors.toList());
    }
}
