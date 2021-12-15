package com.edcm.backend.infrastructure.domain.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubCommodityItem {
    @JsonProperty("id")
    private String id;
    @JsonProperty("category")
    private String category;
    @JsonProperty("name")
    private String name;
}
