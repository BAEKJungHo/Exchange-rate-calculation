package com.wirebarley.jungho.web.validator;

import com.wirebarley.jungho.domain.dto.ExchangeRateCalculatingRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ExchangeRateCalculatingValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ExchangeRateCalculatingRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExchangeRateCalculatingRequest dto = (ExchangeRateCalculatingRequest) target;

        if(dto.getRemittanceMoney() == null || dto.getRemittanceMoney() <= 0 || dto.getRemittanceMoney() > 10000) {
            errors.rejectValue("remittanceMoney", "range", new Object[]{1, 10000}, "송금액이 바르지 않습니다.");
        }
    }
}
