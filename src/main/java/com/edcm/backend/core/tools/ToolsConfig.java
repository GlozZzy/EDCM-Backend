package com.edcm.backend.core.tools;

import com.edcm.backend.extensions.mappers.CommoditiesDataMapper;
import com.edcm.backend.infrastructure.github.DefaultGithubDataProvider;
import com.edcm.backend.infrastructure.github.GithubOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ToolsConfig {
    private final GithubOperations githubOperations;
    private final CommoditiesDataMapper mapper;

    @Bean
    public GithubDataProvider githubDataProvider(GithubOperations githubOperations, CommoditiesDataMapper mapper){
        return new DefaultGithubDataProvider(githubOperations, mapper);
    }
}
