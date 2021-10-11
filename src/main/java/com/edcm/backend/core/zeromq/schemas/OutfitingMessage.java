package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OutfitingMessage {
    @JsonProperty("horizons")
    private boolean horizons;
    @JsonProperty("marketId")
    private long marketId;
    @JsonProperty("modules")
    private List<String> modules;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("systemName")
    private String systemName;
    @JsonProperty("timestamp")
    private String timestamp;
}
