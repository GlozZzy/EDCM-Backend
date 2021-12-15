package com.edcm.backend.infrastructure.github;

import com.edcm.backend.infrastructure.domain.github.GithubCommodityItem;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityItemWithEddn;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DefaultGithubOperations implements GithubOperations {

    @Qualifier("githubCommoditiesWebClient")
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Override
    public GithubCommodityResponse getCommodities() {
        String response = webClient
            .get()
            .retrieve()
            .bodyToMono(String.class)
            .block();

        assert response != null;

        try {
            MapType type = objectMapper
                .getTypeFactory()
                .constructMapType(HashMap.class, String.class, GithubCommodityItem.class);
            return new GithubCommodityResponse(objectMapper.readValue(response, type));
        } catch (JsonProcessingException e) {
            log.error("Failed to get commodities", e);
            return null;
        }
    }
}
