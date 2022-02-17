package com.edcm.backend.core.zeromq;

import com.edcm.backend.core.properties.ZeromqConfigurationProperties;
import com.edcm.backend.core.schedule.CommodityCheckService;
import com.edcm.backend.core.services.ChannelWatcher;
import com.edcm.backend.core.services.commodity.ZeromqCommoditesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.zeromq.channel.ZeroMqChannel;
import org.springframework.messaging.MessageHandler;
import org.zeromq.ZContext;

@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class ZeromqConfig {

    @Bean
    public ZContext zContext() {
        return new ZContext();
    }

    @Bean(name = "zeroMqChannel")
    public ZeroMqChannel zeroMqPubSubChannel(ZContext context, ZeromqConfigurationProperties properties) {
        ZeroMqChannel channel = new ZeroMqChannel(context, true);
        channel.setConnectUrl(properties.getEddnChannelUrl());
        return channel;
    }

    @Bean
    @DependsOn(value = {"channelWatcher", "commodityCheckService"})
    @ServiceActivator(inputChannel = "zeroMqChannel")
    public MessageHandler messageService(
        ObjectMapper objectMapper,
        ZeromqCommoditesService zeromqCommoditesService,
        ChannelWatcher channelWatcher,
        CommodityCheckService commodityCheckService) {

        commodityCheckService.updateCommodities();
        return new ZeromqMessageHandler(objectMapper, zeromqCommoditesService, channelWatcher);
    }
}
