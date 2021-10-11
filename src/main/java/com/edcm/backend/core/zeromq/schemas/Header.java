package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Header {
    @JsonProperty("gatewayTimestamp")
    private String gatewayTimestamp;
    @JsonProperty("softwareName")
    private String softwareName;
    @JsonProperty("softwareVersion")
    private String softwareVersion;
    @JsonProperty("uploaderID")
    private String uploaderID;
}