package com.edcm.backend.core.services;

import com.edcm.backend.core.properties.ZeromqConfigurationProperties;
import com.edcm.backend.core.schedule.ChannelReconnectTask;
import org.springframework.integration.zeromq.channel.ZeroMqChannel;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class ChannelWatcher {
    private final ZeroMqChannel zeroMqChannel;
    private final ZeromqConfigurationProperties properties;
    private Timer timer;

    public ChannelWatcher(ZeroMqChannel zeroMqChannel, ZeromqConfigurationProperties properties) {
        this.zeroMqChannel = zeroMqChannel;
        this.properties = properties;

        ChannelReconnectTask reconnectTask = new ChannelReconnectTask(zeroMqChannel, properties.getEddnChannelUrl());
        this.timer = new Timer();
        timer.schedule(reconnectTask, properties.getTimeout());
    }

    public void resetTimer() {
        timer.cancel();
        timer = new Timer();
        ChannelReconnectTask reconnectTask = new ChannelReconnectTask(zeroMqChannel, properties.getEddnChannelUrl());
        timer.schedule(reconnectTask, properties.getTimeout());
    }
}
