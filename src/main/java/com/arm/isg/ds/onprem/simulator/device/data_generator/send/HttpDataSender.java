package com.arm.isg.ds.onprem.simulator.device.data_generator.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpDataSender implements DataSender {

    private static final Logger log = LoggerFactory.getLogger(HttpDataSender.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public HttpDataSender (String url) {

    }

    @Override
    public void sendDataItem(Map<String, Object> dataMap) {

        log.debug("Sending data {}", generateJsonForDataMap(dataMap));
    }


    private String generateJsonForDataMap(Map<String, Object> dataMap) {
        try {
            return OBJECT_MAPPER.writeValueAsString(dataMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // TODO : Use better exception handling
        }
    }
}
