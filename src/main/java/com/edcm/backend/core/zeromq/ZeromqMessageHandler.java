package com.edcm.backend.core.zeromq;

import com.edcm.backend.core.services.ChannelWatcher;
import com.edcm.backend.core.services.commodity.ZeromqCommoditesService;
import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RequiredArgsConstructor
@Slf4j
public class ZeromqMessageHandler implements MessageHandler {
    private final ObjectMapper objectMapper;
    private final ZeromqCommoditesService zeromqCommoditesService;
    private final ChannelWatcher channelWatcher;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        channelWatcher.resetTimer();

        byte[] output = new byte[256 * 1024];
        byte[] payload = (byte[]) message.getPayload();
        Inflater inflater = new Inflater();
        inflater.setInput(payload);
        try {
            int outputLength = inflater.inflate(output);
            String outputString = new String(output, 0, outputLength, StandardCharsets.UTF_8);
            MessageSchema schemaType = getSchema(outputString);

            switch (schemaType) {
                case COMMODITY -> {
                    try {
                        ZeromqCommodityPayload outfittingPayload = objectMapper.readValue(
                            outputString,
                            ZeromqCommodityPayload.class);
                        zeromqCommoditesService.saveData(outfittingPayload);
                    } catch (JsonProcessingException e) {
                        log.error("Error during json conversion", e);
                    }
                }
            }
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }

    private MessageSchema getSchema(String message) {
        String schemaString = message
            .replace("{", "")
            .split(",")[0]
            .split("\":")[1]
            .replace("\"", "")
            .trim();
        return MessageSchema.valueOf(schemaString);
    }
}
