package com.superzyen.page;

import com.superzyen.mining.MineField;
import com.superzyen.util.IoUtils;

import java.io.IOException;

/**
 *  挖矿页面
 */
public class MiningPage {
    private MineField mineField = new MineField();

    public void mining(HomePage homePage) throws IOException {

        if (!HomePage.getGlobalStatus().isLogined()) {
            IoUtils.println("请先登录");
            homePage.start();
        }

        IoUtils.println("进入算力矿场");
        IoUtils.println("请输入选择:");
        IoUtils.println("1 挖矿十万次");
        IoUtils.println("2 挖矿百万次次");
        IoUtils.println("3 无限次");
        IoUtils.println("4 返回上一层");
        int select = IoUtils.inputInt();
        int loop = 0;
        switch (select) {
            case 1:
                loop = 100000;
                break;
            case 2:
                loop = 1000000;
                break;
            case 3:
                loop = -1;
                break;
            default:
                homePage.start();
                break;
        }
        mineField.mining(loop);
        this.mining(homePage);
    }
}
