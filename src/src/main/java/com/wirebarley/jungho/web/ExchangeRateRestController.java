package com.wirebarley.jungho.web;

import com.wirebarley.jungho.domain.ExchangeRate;
import com.wirebarley.jungho.domain.dto.ExchangeRateRequest;
import com.wirebarley.jungho.service.ExchangeRateFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/exchangeRates")
@RestController
public class ExchangeRateRestController {

    private final ExchangeRateFindService exchangeRateFindService;

    @PostMapping("/{currency}")
    public ResponseEntity<Double> findExchangeRateByCurrency(@PathVariable String currency) {
        ExchangeRate exchangeRate = exchangeRateFindService.findExchangeRate();
        Double result = exchangeRate.findRates(currency);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public String send(@RequestBody ExchangeRateRequest.Sending dto) {
        return "ok";
    }
}
