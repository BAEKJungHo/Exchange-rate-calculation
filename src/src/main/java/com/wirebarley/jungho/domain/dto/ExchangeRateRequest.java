package com.wirebarley.jungho.domain.dto;

import com.wirebarley.jungho.domain.Currency;
import lombok.Getter;
import lombok.Setter;

public class ExchangeRateRequest {

    /**
     * 수취 국가로 송금액을 보내기 위한 요청을 처리하기 위한 내부 클래스
     */
    @Getter @Setter
    public static class Sending {
        private Currency receivingCountry;
    }
}
