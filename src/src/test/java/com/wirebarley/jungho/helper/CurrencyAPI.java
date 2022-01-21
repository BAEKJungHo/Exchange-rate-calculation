package com.wirebarley.jungho.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyAPI {

    public static Map<String, Object> call(String endPoint) {
        Map<String, Object> resultMap;
        try {
            URL url = new URL(endPoint);
            ObjectMapper mapper;
            JsonNode rootNode;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8.name()))) {
                mapper = new ObjectMapper();
                rootNode = mapper.readValue(br.readLine(), JsonNode.class);
                resultMap = mapper.readValue(rootNode.toString(), HashMap.class);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return resultMap;
    }
}
