package com.arm.isg.ds.onprem.simulator.device.data_generator.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpDataSender implements DataSender {

    private static final Logger log = LoggerFactory.getLogger(HttpDataSender.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String url;

    public HttpDataSender (String url) {
        this.url = url;
    }

    @Override
    public void sendDataItem(Map<String, Object> dataMap) {

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            log.debug("url = {}", url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

            String jsonStrDataMap = generateJsonForDataMap(dataMap);
            String entityBody = "json=" + jsonStrDataMap;
            log.debug("entityBody: {}", entityBody);
            StringEntity entity = new StringEntity(entityBody);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            log.info ("Received status code = {}", response.getStatusLine().getStatusCode());

            httpClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String generateJsonForDataMap(Map<String, Object> dataMap) {
        try {
            return OBJECT_MAPPER.writeValueAsString(dataMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // TODO : Use better exception handling
        }
    }
}
