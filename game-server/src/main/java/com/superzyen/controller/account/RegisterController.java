package com.superzyen.controller.account;

import com.superzyen.domain.request.RegisterRequest;
import com.superzyen.service.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public Boolean register(@RequestBody RegisterRequest request) {
        return registerService.register(request.getName(), request.getPassword());
    }

    @PostMapping("/canRegister")
    public Boolean canRegister(@RequestBody RegisterRequest request) {
        return registerService.canRegister(request.getName());
    }

}
