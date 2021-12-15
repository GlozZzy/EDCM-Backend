package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @JsonProperty("buyPrice")
    private long buyPrice;
    @JsonProperty("demand")
    private long demand;
    @JsonProperty("demandBracket")
    private long demandBracket;
    @JsonProperty("meanPrice")
    private long meanPrice;
    @JsonProperty("name")
    private String eddnName;
    @JsonProperty("sellPrice")
    private long sellPrice;
    @JsonProperty("stock")
    private long stock;
    @JsonProperty("stockBracket")
    private long stockBracket;
}
