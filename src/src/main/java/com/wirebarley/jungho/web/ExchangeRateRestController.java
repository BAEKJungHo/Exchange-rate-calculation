package com.wirebarley.jungho.web;

import com.wirebarley.jungho.domain.dto.ExchangeRateFindResponse;
import com.wirebarley.jungho.domain.dto.ReceivingMoneyRequest;
import com.wirebarley.jungho.domain.dto.ReceivingMoneyResponse;
import com.wirebarley.jungho.exception.ValidationException;
import com.wirebarley.jungho.service.ExchangeRateFindService;
import com.wirebarley.jungho.web.validator.ReceivingMoneyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/exchangeRates")
@RestController
public class ExchangeRateRestController {

    private final ExchangeRateFindService exchangeRateFindService;
    private final ReceivingMoneyValidator receivingMoneyValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(receivingMoneyValidator);
    }

    @GetMapping(value = "/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateFindResponse> showExchangeRate(@PathVariable String currency) {
        return ResponseEntity.ok().body(exchangeRateFindService.findExchangeRateByCurrency(currency));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceivingMoneyResponse> showReceivingMoney(
            @Valid ReceivingMoneyRequest dto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return ResponseEntity.ok().body(exchangeRateFindService.findReceivingMoney(dto));
    }
}
