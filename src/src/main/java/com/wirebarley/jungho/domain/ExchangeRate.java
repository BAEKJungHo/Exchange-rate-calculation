package com.wirebarley.jungho.domain;

import com.wirebarley.jungho.constants.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
public class ExchangeRate {

    private String terms;
    private boolean success;
    private String privacy;
    private String source;
    private LocalDateTime timestamp;
    private Map<String, Double> quotes;

    /**
     * 환율 조회(요금 찾기)
     * @param target Currency name
     * @return 환율
     */
    public Double findRates(final String target) {
        if(Currency.isExists(target)) {
            return quotes.get(source + target);
        }
        throw new IllegalArgumentException(Message.NOT_FOUND_CURRENCY.getMessage());
    }
}
