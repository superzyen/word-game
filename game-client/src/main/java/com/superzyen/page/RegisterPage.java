package com.superzyen.page;

import com.superzyen.service.account.UserRegister;
import com.superzyen.util.IoUtils;

import java.io.IOException;

public class RegisterPage {

    public void register(HomePage homePage) throws IOException {
        IoUtils.println("请输入账号");
        String username = IoUtils.inputString();
        IoUtils.println("请输入密码");
        String password = IoUtils.inputString();
        UserRegister userRegister = new UserRegister();
        boolean res = userRegister.register(username, password);
        if (res) {
            IoUtils.println("注册成功,请登录...");
            new LoginPage().login(homePage);
        } else {
            IoUtils.println("注册失败");
            homePage.start();
        }
    }
}
