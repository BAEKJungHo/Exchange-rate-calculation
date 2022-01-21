package com.wirebarley.jungho.web;

import com.wirebarley.jungho.helper.CurrencyAPI;
import com.wirebarley.jungho.util.NumberFormatter;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExchangeRateRestControllerTest {

    @Value("${currencyLayer.endPoint}")
    private String endPoint;

    private Map<String, Object> apiResult;

    @BeforeEach
    @Test
    void setFixtures() {
        apiResult = CurrencyAPI.call(endPoint);
    }

    @DisplayName("Currency Name 에 해당하는 환율 찾기")
    @Test
    void findExchangeRateByCurrencyName() {
        // given
        String currency = "JPY";

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/exchangeRates/" + currency)
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        String exchangeRates = response.jsonPath().get("exchangeRates");
        assertThat(exchangeRates).isNotNull();
    }

    @DisplayName("수취국가와 송금액이 주어지면 수취금액을 계산하기")
    @Test
    void calculatedReceipts() {
        // given
        String currency = "KRW";
        int remittanceMoney = 100;
        Map<String, Object> params = new HashMap<>();
        params.put("receivingCountry", currency);
        params.put("remittanceMoney", remittanceMoney);

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(params)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/exchangeRates/")
                .then().log().all()
                .extract();

        // and
        Map<String, Object> result = CurrencyAPI.call(endPoint);
        Map<String, Double> quotes = (HashMap) result.get("quotes");
        String source = (String) result.get("source");
        String quotesKey = source + currency;
        double exchangeRate2 = quotes.get(quotesKey);
        double receipts2 = exchangeRate2 * remittanceMoney;
        String formattedKReceipts = NumberFormatter.moneyFormat(receipts2);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        String receipts = response.jsonPath().get("receipts");
        assertThat(receipts).isEqualTo(formattedKReceipts);
    }

}