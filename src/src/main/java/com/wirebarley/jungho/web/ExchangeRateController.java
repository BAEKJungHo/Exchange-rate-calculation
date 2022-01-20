package com.wirebarley.jungho.web;

import com.wirebarley.jungho.domain.Currency;
import com.wirebarley.jungho.domain.ExchangeRate;
import com.wirebarley.jungho.domain.ReceivingCountry;
import com.wirebarley.jungho.service.ExchangeRateFindService;
import com.wirebarley.jungho.web.form.ExchangeRateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.wirebarley.jungho.constants.SessionAttributeNames.*;

/**
 * 환율 컨트롤러
 */
@RequiredArgsConstructor
@RequestMapping("/exchangeRates")
@Controller
public class ExchangeRateController {

    private final ExchangeRateFindService exchangeRateFindService;

    /**
     * 환율 계산 폼
     * @param model Model
     * @param request HttpServletRequest
     * @return 환율 계산 폼
     */
    @GetMapping(value = "/form", produces = MediaType.TEXT_HTML_VALUE)
    public String form(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ExchangeRate exchangeRate = (ExchangeRate) session.getAttribute(EXCHANGE_RATE);
        if(exchangeRate == null) {
            exchangeRate = exchangeRateFindService.findExchangeRate();
            session.setAttribute(EXCHANGE_RATE, exchangeRate);
        }

        model.addAttribute("krwExchangeRate", exchangeRate.findRates(Currency.KRW.name()));
        model.addAttribute("receivingCountries", List.of(ReceivingCountry.values()));
        model.addAttribute("exchangeRateForm", new ExchangeRateForm());

        return "exchangeRates/form";
    }
}
