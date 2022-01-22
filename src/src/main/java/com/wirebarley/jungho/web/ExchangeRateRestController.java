package com.wirebarley.jungho.web;

import com.wirebarley.jungho.domain.ExchangeRate;
import com.wirebarley.jungho.domain.dto.ExchangeRateCalculatingRequest;
import com.wirebarley.jungho.domain.dto.ExchangeRateCalculatingResponse;
import com.wirebarley.jungho.domain.dto.ExchangeRateFindResponse;
import com.wirebarley.jungho.exception.ValidationException;
import com.wirebarley.jungho.service.ExchangeRateFindService;
import com.wirebarley.jungho.util.NumberFormatter;
import com.wirebarley.jungho.web.validator.ExchangeRateCalculatingValidator;
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
    private final ExchangeRateCalculatingValidator exchangeRateCalculatingValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(exchangeRateCalculatingValidator);
    }

    @GetMapping(value = "/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateFindResponse> showExchangeRate(@PathVariable String currency) {
        ExchangeRate exchangeRate = exchangeRateFindService.findExchangeRate();
        ExchangeRateFindResponse response = ExchangeRateFindResponse.builder()
                        .exchangeRates(NumberFormatter.moneyFormat(exchangeRate.findRates(currency)))
                        .source(exchangeRate.getSource())
                        .currency(currency)
                        .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateCalculatingResponse> showReceivingMoney(
            @Valid ExchangeRateCalculatingRequest dto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        ExchangeRate exchangeRate = exchangeRateFindService.findExchangeRate();
        String receipts = NumberFormatter.moneyFormat(exchangeRate.findReceipts(dto.getCurrency(), dto.getRemittanceMoney()));

        ExchangeRateCalculatingResponse response = ExchangeRateCalculatingResponse.builder()
                .receipts(receipts)
                .currency(dto.getCurrency())
                .build();
        return ResponseEntity.ok().body(response);
    }
}
