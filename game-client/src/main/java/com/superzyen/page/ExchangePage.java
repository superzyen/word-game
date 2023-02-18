package com.superzyen.page;

import com.superzyen.dto.AccountDto;
import com.superzyen.service.exchange.Exchange;
import com.superzyen.util.AuthUtils;

import java.io.IOException;

public class ExchangePage {

    private Exchange exchange = new Exchange();

    public void start(HomePage homePage) throws IOException {
        AccountDto accountDto = AuthUtils.isLogin(homePage);
        exchange.operate(exchange.list());
        homePage.start();
    }
}
