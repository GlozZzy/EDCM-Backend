package com.edcm.backend.core.zeromq.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    @JsonProperty("gatewayTimestamp")
    private LocalDate gatewayTimestamp;
    @JsonProperty("softwareName")
    private String softwareName;
    @JsonProperty("softwareVersion")
    private String softwareVersion;
    @JsonProperty("uploaderID")
    private String uploaderID;
}