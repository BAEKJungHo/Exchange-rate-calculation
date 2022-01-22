package com.wirebarley.jungho.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceivingMoneyResponse {

    private String receipts;
    private String currency;
}
