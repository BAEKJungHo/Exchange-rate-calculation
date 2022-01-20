package com.wirebarley.jungho.domain;

import com.wirebarley.jungho.constants.Message;

/**
 * 통화
 *
 * <ul>
 * <li> 각 나라의 통화에 대한 표기를 가지고 있다.
 * </ul>
 */
public enum Currency {

    KRW,
    JPY,
    PHP,
    ;

    public static boolean isExists(final String target) {
        for(Currency currency : Currency.values()) {
            if(currency.name().equals(target)) {
                return true;
            }
        }
        throw new IllegalArgumentException(Message.NOT_FOUND_CURRENCY.getMessage());
    }
}
