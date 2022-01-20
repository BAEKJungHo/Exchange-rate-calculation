package com.wirebarley.jungho.service;

import com.wirebarley.jungho.domain.Currency;
import com.wirebarley.jungho.domain.ExchangeRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeRateFindServiceTest {

    @Value("${currencyLayer.endPoint}")
    private String endPoint;

    @Autowired
    private ExchangeRateFindService exchangeRateFindService;

    @DisplayName("CurrencyLayer API endPoint 테스트")
    @Test
    void currencyLayerApiEndpointTest() throws Exception {
        URL url = new URL(endPoint);
        assertNotNull(url);
    }

    @DisplayName("KRW 환율 테스트")
    @Test
    void krwExchangeRateTest() {
        ExchangeRate exchangeRate = exchangeRateFindService.findExchangeRate();
        assertEquals(exchangeRate.findRates(Currency.KRW.name()), 1189.835062);
    }
}