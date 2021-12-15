package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Economy {
    @JsonProperty("name")
    private String name;
    @JsonProperty("proportion")
    private Double proportion;
}
