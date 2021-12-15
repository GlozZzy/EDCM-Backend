package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutfittingContent {
    @JsonProperty("horizons")
    private boolean horizons;
    @JsonProperty("marketId")
    private long marketId;
    @JsonProperty("modules")
    private List<OutfittingContent> modules;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("systemName")
    private String systemName;
    @JsonProperty("timestamp")
    private String timestamp;
}
