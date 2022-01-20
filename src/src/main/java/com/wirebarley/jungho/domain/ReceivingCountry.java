package com.wirebarley.jungho.domain;

import lombok.Getter;

/**
 * 수취 국가
 *
 * <ul>
 * <li> 수취 국가는 각 나라를 의미하는 enum 을 가지고 있다.
 * <li> 수취 국가는 {@code Currency} 에 의존한다.
 * </ul>
 */
@Getter
public enum ReceivingCountry {

    KOREA("한국", Currency.KRW),
    JAPAN("일본", Currency.JPY),
    PHILIPPINES("필리핀", Currency.PHP),
    ;

    private String countryName;
    private Currency currency;

    ReceivingCountry(final String countryName, final Currency currency) {
        this.countryName = countryName;
        this.currency = currency;
    }
}
