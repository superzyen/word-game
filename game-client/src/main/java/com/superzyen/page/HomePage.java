package com.superzyen.page;

import com.superzyen.common.GlobalStatus;
import com.superzyen.util.IoUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomePage {

    private static GlobalStatus globalStatus =null;

    public void start() throws IOException {

        init();

        IoUtils.println("请输入你的选择");
        IoUtils.println("1 注册");
        IoUtils.println("2 登录");
        IoUtils.println("3 钱包");
        IoUtils.println("4 挖矿");
        int select = IoUtils.inputInt();
        switch (select){
            case 1:
                new RegisterPage().register(this);
                break;
            case 2:
                new LoginPage().login(this);
                break;
            case 3:
                new WalletPage().show(this);
                break;
            case 4:
                new MiningPage().mining(this);
                break;
            default:
                this.start();
                break;
        }


    }

    public static GlobalStatus getGlobalStatus() {
        return globalStatus;
    }

    private void init() {
        if (globalStatus == null) {
            this.globalStatus = new GlobalStatus();
        }
    }
}
