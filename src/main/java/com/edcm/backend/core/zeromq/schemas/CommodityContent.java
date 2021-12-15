package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommodityContent {
    @JsonProperty("commodities")
    private List<Commodity> commodities;
    @JsonProperty("economies")
    private List<Economy> economies;
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
