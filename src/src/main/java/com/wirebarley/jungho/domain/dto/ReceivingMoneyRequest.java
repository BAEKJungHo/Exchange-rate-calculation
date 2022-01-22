package com.wirebarley.jungho.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReceivingMoneyRequest {

    private String currency;
    private Integer remittanceMoney;
}
