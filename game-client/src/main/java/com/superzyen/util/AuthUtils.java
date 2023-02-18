package com.superzyen.util;

import com.superzyen.dto.AccountDto;
import com.superzyen.page.HomePage;

import java.io.IOException;

/**
 * 权限验证
 */
public class AuthUtils {

    public static AccountDto isLogin(HomePage homePage) throws IOException {
        AccountDto accountDto = CommonApiUtils.userInfo();
        if (null == accountDto) {
            IoUtils.println("请先登录！");
            homePage.start();
        }
        return accountDto;
    }
}
