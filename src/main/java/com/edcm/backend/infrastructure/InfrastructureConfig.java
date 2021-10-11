package com.edcm.backend.infrastructure;

import com.edcm.backend.infrastructure.github.DefaultGithubOperations;
import com.edcm.backend.infrastructure.github.GithubOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class InfrastructureConfig {
    @Value("${values.github-commodities-url}")
    private String githubCommoditiesUrl;

    @Bean
    @Qualifier("githubCommoditiesWebClient")
    public WebClient webClient() {
        return WebClient.create(githubCommoditiesUrl);
    }

    @Bean
    public GithubOperations githubOperations(@Qualifier("githubCommoditiesWebClient") WebClient githubWebClient) {
        return new DefaultGithubOperations(githubWebClient);
    }
}
