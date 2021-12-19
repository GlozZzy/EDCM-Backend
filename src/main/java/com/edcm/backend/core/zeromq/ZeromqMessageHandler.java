package com.edcm.backend.core.zeromq;

import com.edcm.backend.core.services.CategoryTransactionHandler;
import com.edcm.backend.core.services.CommodityTransactionHandler;
import com.edcm.backend.core.services.EconomyTransactionHandler;
import com.edcm.backend.core.services.StationTransactionHandler;
import com.edcm.backend.core.services.SystemTransactionHandler;
import com.edcm.backend.core.services.ZeromqCommoditesService;
import com.edcm.backend.core.zeromq.schemas.CommodityContent;
import com.edcm.backend.core.zeromq.schemas.ZeromqCommodityPayload;
import com.edcm.backend.infrastructure.domain.database.entities.StationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RequiredArgsConstructor
@Slf4j
public class ZeromqMessageHandler implements MessageHandler {
    private final ObjectMapper objectMapper;
    private final ZeromqCommoditesService zeromqCommoditesService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        byte[] output = new byte[256 * 1024];
        byte[] payload = (byte[]) message.getPayload();
        Inflater inflater = new Inflater();
        inflater.setInput(payload);
        try {
            int outputLength = inflater.inflate(output);
            String outputString = new String(output, 0, outputLength, StandardCharsets.UTF_8);
            String schemaType = getSchema(outputString);

            switch (schemaType) {
                case "https://eddn.edcd.io/schemas/commodity/3" -> {
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

    private String getSchema(String message) {
        return message
            .replace("{", "")
            .split(",")[0]
            .split("\":")[1]
            .replace("\"", "")
            .trim();
    }
}
