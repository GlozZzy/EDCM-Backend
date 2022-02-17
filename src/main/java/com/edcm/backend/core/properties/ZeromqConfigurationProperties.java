package com.edcm.backend.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "zeromq")
@Configuration
@Data
public class ZeromqConfigurationProperties {
    private String eddnChannelUrl;
    private long timeout;
}
