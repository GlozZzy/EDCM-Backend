package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommodityMessage {
    @JsonProperty("commodities")
    private Commodity[] commodities;
    @JsonProperty("economies")
    private Economy[] economies;
    @JsonProperty("marketId")
    private long marketId;
    @JsonProperty("odyssey")
    private boolean odyssey;
    @JsonProperty("prohibited")
    private List<String> prohibited = null;
    @JsonProperty("stationName")
    private String stationName;
    @JsonProperty("systemName")
    private String systemName;
    @JsonProperty("timestamp")
    private Date timestamp;
}
