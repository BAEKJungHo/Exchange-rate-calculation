package com.wirebarley.jungho.service;

import com.wirebarley.jungho.acceptance.AcceptanceTest;
import com.wirebarley.jungho.helper.CurrencyAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("환율 조회 서비스 테스트")
class ExchangeRateFindServiceTest extends AcceptanceTest {

    @Value("${currencyLayer.endPoint}")
    private String endPoint;

    @DisplayName("CurrencyLayer API endPoint 연결 테스트")
    @Test
    void currencyLayerApiEndpoint() throws Exception {
        URL url = new URL(endPoint);
        assertNotNull(url);
    }

    @DisplayName("KRW 환율 테스트")
    @Test
    void krwExchangeRate() {
        Map<String, Object> result = CurrencyAPI.call(endPoint);
        Map<String, Double> quotes = (HashMap) result.get("quotes");

        String source = (String) result.get("source");
        String currency = "KRW";
        String quotesKey = source + currency;

        assertThat(quotes.get(quotesKey)).isNotNull();
    }
}