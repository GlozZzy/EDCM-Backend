package com.edcm.backend.infrastructure.github;

import com.edcm.backend.infrastructure.domain.github.GithubCommodityResponse;

public interface GithubOperations {
    GithubCommodityResponse getCommodities();
}
