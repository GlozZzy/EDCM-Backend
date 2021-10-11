package com.edcm.backend.infrastructure.github;

import com.edcm.backend.infrastructure.domain.github.GithubCommodityItem;
import com.edcm.backend.infrastructure.domain.github.GithubCommodityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultGithubOperations implements GithubOperations {

    @Qualifier("githubCommoditiesWebClient")
    private final WebClient webClient;

    @Override
    public GithubCommodityResponse getCommodities() {
        String response = webClient
            .get()
            .retrieve()
            .bodyToMono(String.class)
            .block();

        assert response != null;
        List<GithubCommodityItem> responseItems = Arrays.stream(response
            .split("\r\n"))
            .skip(1)
            .map(line -> {
                String[] elements = line.split(",");
                return new GithubCommodityItem(elements[0], elements[1], elements[2], elements[3]);
            })
            .collect(Collectors.toList());
        return new GithubCommodityResponse(responseItems);
    }
}
