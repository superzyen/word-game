package com.superzyen.controller.account;

import com.superzyen.domain.request.LoginRequest;
import com.superzyen.service.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Boolean login(@RequestBody LoginRequest request) {
        return loginService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/userInfo")
    public String userInfo(@RequestBody LoginRequest request) {
        return loginService.userInfo(request.getUsername(), request.getPassword());
    }
}
