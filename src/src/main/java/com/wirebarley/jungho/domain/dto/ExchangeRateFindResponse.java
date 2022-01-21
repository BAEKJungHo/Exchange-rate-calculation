package com.wirebarley.jungho.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExchangeRateFindResponse {

    private String exchangeRates;
    private String source;
    private String currency;
}
