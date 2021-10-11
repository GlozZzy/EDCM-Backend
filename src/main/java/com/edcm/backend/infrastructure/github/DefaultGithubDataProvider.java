package com.edcm.backend.infrastructure.github;

import com.edcm.backend.core.dto.CommoditySharedData;
import com.edcm.backend.core.tools.GithubDataProvider;
import com.edcm.backend.extensions.mappers.CommoditiesDataMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultGithubDataProvider implements GithubDataProvider {
    private final GithubOperations githubOperations;
    private final CommoditiesDataMapper mapper;

    @Override
    public List<CommoditySharedData> getCommodities() {
        return githubOperations.getCommodities()
            .getItems()
            .stream()
            .skip(1)
            .map(mapper::toCommoditySharedData)
            .collect(Collectors.toList());
    }
}
