package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZeromqMessage {
    @JsonProperty("$schemaRef")
    private String schemaRef;
    @JsonProperty("header")
    private Header header;
    @JsonProperty("message")
    private CommodityMessage message;

}
