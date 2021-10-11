package com.edcm.backend.infrastructure.domain.github;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubCommodityItem {
    private String id;
    private String symbol;
    private String category;
    private String name;

    public GithubCommodityItem(String id, String symbol, String category, String name) {
        this.id = id;
        this.symbol = symbol;
        this.category = category;
        this.name = name;
    }
}
