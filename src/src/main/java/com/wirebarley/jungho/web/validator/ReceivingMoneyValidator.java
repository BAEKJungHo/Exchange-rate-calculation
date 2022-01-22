package com.wirebarley.jungho.web.validator;

import com.wirebarley.jungho.domain.dto.ReceivingMoneyRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReceivingMoneyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ReceivingMoneyRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReceivingMoneyRequest dto = (ReceivingMoneyRequest) target;

        if(dto.getRemittanceMoney() == null || dto.getRemittanceMoney() <= 0 || dto.getRemittanceMoney() > 10000) {
            errors.rejectValue("remittanceMoney", "range", new Object[]{1, 10000}, "송금액이 바르지 않습니다.");
        }
    }
}
