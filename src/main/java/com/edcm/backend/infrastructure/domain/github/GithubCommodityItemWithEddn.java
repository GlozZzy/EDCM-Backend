package com.edcm.backend.infrastructure.domain.github;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubCommodityItemWithEddn {
    private String id;
    private String category;
    private String name;
    private String eddnName;
}
