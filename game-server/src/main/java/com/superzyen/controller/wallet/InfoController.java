package com.superzyen.controller.wallet;

import com.superzyen.domain.request.InfoRequest;
import com.superzyen.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Autowired
    WalletService walletService;

    @PostMapping("/show")
    public String show(@RequestBody InfoRequest request) {
        return walletService.show(request.getUserId());
    }
}
