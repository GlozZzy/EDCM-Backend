package com.edcm.backend.core.zeromq;

import com.edcm.backend.core.schedule.CommodityCheckService;
import com.edcm.backend.core.services.CategoryTransactionHandler;
import com.edcm.backend.core.services.CommodityTransactionHandler;
import com.edcm.backend.core.services.EconomyTransactionHandler;
import com.edcm.backend.core.services.StationTransactionHandler;
import com.edcm.backend.core.services.SystemTransactionHandler;
import com.edcm.backend.core.services.ZeromqCommoditesService;
import com.edcm.backend.infrastructure.domain.database.repositories.ProhibitedCommodityRepository;
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
    public ZeroMqChannel zeroMqPubSubChannel(ZContext context) {
        ZeroMqChannel channel = new ZeroMqChannel(context, true);
        channel.setConnectUrl("tcp://eddn.edcd.io:9500:9500");
        return channel;
    }

    @Bean
    @DependsOn("commodityCheckService")
    @ServiceActivator(inputChannel = "zeroMqChannel")
    public MessageHandler messageHandler(
        ObjectMapper objectMapper,
        ZeromqCommoditesService zeromqCommoditesService,
        CommodityCheckService commodityCheckService) {
        //commodityCheckService.updateCommodities();
        return new ZeromqMessageHandler(objectMapper, zeromqCommoditesService);
    }
}
