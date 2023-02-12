package com.superzyen.page;

import com.superzyen.account.UserLogin;
import com.superzyen.util.IoUtils;

import java.io.IOException;

public class LoginPage {

    public void login(HomePage homePage) throws IOException {
        IoUtils.println("请输入账号");
        String username = IoUtils.inputString();
        IoUtils.println("请输入密码");
        String password = IoUtils.inputString();
        UserLogin userLogin = new UserLogin();
        boolean res = userLogin.login(username, password);
        if (res) {
            IoUtils.println("登录成功");
            HomePage.getGlobalStatus().setLogined(true);
            homePage.start();
        }else{
            IoUtils.println("登录失败");
            homePage.start();
        }
    }
}
