package com.edcm.backend.core.schedule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.zeromq.channel.ZeroMqChannel;

import java.util.TimerTask;

@AllArgsConstructor
@Slf4j
public class ChannelReconnectTask extends TimerTask {
    private final ZeroMqChannel channel;
    private final String url;

    @Override
    public void run() {
        channel.setConnectUrl(url);
        log.info("Reconnected to EDDN");
    }
}
