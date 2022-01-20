package com.wirebarley.jungho.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Message {

    EXCHANGE_RATE_PARSING_FAIL("환율 정보를 가져오는데 실패하였습니다."),
    NOT_FOUND_CURRENCY("통화를 찾지 못하였습니다."),
    NOT_FOUND_EXCHANGE_RATE("환율을 찾지 못하였습니다.")
    ;

    private final String message;
}
