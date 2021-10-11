package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Economy {
    @JsonProperty("name")
    private String name;
    @JsonProperty("proportion")
    private Double proportion;
}
