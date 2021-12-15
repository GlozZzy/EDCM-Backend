package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZeromqCommodityPayload {

    @JsonProperty("$schemaRef")
    private String schemaRef;

    @JsonProperty("header")
    private Header header;

    @JsonProperty("message")
    private CommodityContent content;
}
