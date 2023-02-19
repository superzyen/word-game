package com.superzyen.page.battle;

import com.superzyen.page.HomePage;
import com.superzyen.util.IoUtils;
import lombok.Data;

import java.io.IOException;

@Data
public class BattlePage {

    private HomePage homePage = null;

    public void start(HomePage homePage) throws IOException {
        if (null == this.homePage) {
            this.homePage = homePage;
        }

        IoUtils.println("1 爬塔");
        IoUtils.println("2 排行榜");
        IoUtils.println("其他 返回上一页");
        IoUtils.println("请输入你的选择");
        int select = IoUtils.inputInt();
        switch (select) {
            case 1:
                new TowePage().climb(this);
                break;
            case 2:
                new TwoerRankPage().show(this);
                break;
            default:
                homePage.start();
                break;
        }
    }
}
