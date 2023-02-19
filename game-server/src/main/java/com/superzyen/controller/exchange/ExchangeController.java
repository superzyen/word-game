package com.superzyen.controller.exchange;

import com.superzyen.domain.request.ExchangeRequest;
import com.superzyen.service.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/list")
    public String login() {
        return exchangeService.list();
    }

    @PostMapping("/buy")
    public String buy(@RequestBody ExchangeRequest request) {
        request.setTime(new Date());
        return exchangeService.buy(request);
    }
}
