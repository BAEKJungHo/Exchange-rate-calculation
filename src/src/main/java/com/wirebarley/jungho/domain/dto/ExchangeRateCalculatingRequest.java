package com.wirebarley.jungho.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExchangeRateCalculatingRequest {

    private String receivingCountry;
    private Integer remittanceMoney;
}
