package com.superzyen.page;

import com.superzyen.util.IoUtils;
import com.superzyen.service.wallet.WalletInfo;

import java.io.IOException;

public class WalletPage {
    private WalletInfo walletInfo = new WalletInfo();

    public void show(HomePage homePage) throws IOException {
        if (!HomePage.getGlobalStatus().isLogined()) {
            IoUtils.println("请先登录！");
            homePage.start();
            return;
        }

        IoUtils.println("查询中....");
        walletInfo.show();
        homePage.start();
    }
}
