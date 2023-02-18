package com.superzyen.controller.exchange;

import com.superzyen.domain.request.LoginRequest;
import com.superzyen.service.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/list")
    public String login() {
        return exchangeService.list();
    }
}
