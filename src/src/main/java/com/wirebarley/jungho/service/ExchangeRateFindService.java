package com.wirebarley.jungho.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wirebarley.jungho.constants.Message;
import com.wirebarley.jungho.domain.dto.ExchangeRateFindResponse;
import com.wirebarley.jungho.domain.dto.ReceivingMoneyRequest;
import com.wirebarley.jungho.domain.dto.ReceivingMoneyResponse;
import com.wirebarley.jungho.exception.WirebarleyException;
import com.wirebarley.jungho.domain.ExchangeRate;
import com.wirebarley.jungho.util.DateUtils;
import com.wirebarley.jungho.util.NumberFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 환율 조회 서비스
 */
@Slf4j
@Service
public class ExchangeRateFindService {

    @Value("${currencyLayer.endPoint}")
    private String endPoint;

    /**
     * 통화로 환율 조회
     * @param currency 통화
     * @return 통화에 대한 환율
     */
    public ExchangeRateFindResponse findExchangeRateByCurrency(String currency) {
        ExchangeRate exchangeRate = findExchangeRate();
        return ExchangeRateFindResponse.builder()
                .exchangeRates(NumberFormatter.moneyFormat(exchangeRate.findRates(currency)))
                .source(exchangeRate.getSource())
                .currency(currency)
                .build();
    }

    /**
     * 수취 금액 조회
     * @param dto ReceivingMoneyRequest
     * @return 수취 금액
     */
    public ReceivingMoneyResponse findReceivingMoney(ReceivingMoneyRequest dto) {
        ExchangeRate exchangeRate = findExchangeRate();
        String receipts = NumberFormatter.moneyFormat(exchangeRate.findReceipts(dto.getCurrency(), dto.getRemittanceMoney()));
        return ReceivingMoneyResponse.builder()
                .receipts(receipts)
                .currency(dto.getCurrency())
                .build();
    }

    /**
     * 환율 조회
     * @return 환율(ExchangeRate)
     */
    public ExchangeRate findExchangeRate() {
        ExchangeRate exchangeRate;
        try {
            URL url = new URL(endPoint);
            ObjectMapper mapper;
            JsonNode rootNode;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8.name()))) {
                mapper = new ObjectMapper();
                rootNode = mapper.readValue(br.readLine(), JsonNode.class);
                Map<String, Object> resultMap = mapper.readValue(rootNode.toString(), HashMap.class);
                Map<String, Double> quotes = (HashMap) resultMap.get("quotes");

                exchangeRate = ExchangeRate.builder()
                        .success((Boolean) resultMap.get("success"))
                        .terms((String) resultMap.get("terms"))
                        .privacy((String) resultMap.get("privacy"))
                        .source((String) resultMap.get("source"))
                        .timestamp(DateUtils.timestampToLocalDateTime((Integer) resultMap.get("timestamp")))
                        .quotes(quotes)
                        .build();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new WirebarleyException(Message.EXCHANGE_RATE_PARSING_FAIL.getMessage());
        }
        return exchangeRate;
    }
}
