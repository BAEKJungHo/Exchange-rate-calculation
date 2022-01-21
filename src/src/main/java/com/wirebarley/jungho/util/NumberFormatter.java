package com.wirebarley.jungho.util;

import lombok.RequiredArgsConstructor;
import org.thymeleaf.expression.Numbers;
import org.thymeleaf.util.NumberPointType;

import java.util.Locale;

@RequiredArgsConstructor
public final class NumberFormatter {

    public static String moneyFormat(final double target) {
        Numbers numbers = new Numbers(Locale.KOREA);
        return numbers.formatDecimal(target, 0, NumberPointType.COMMA.getName(), 2, NumberPointType.POINT.getName());
    }
}
