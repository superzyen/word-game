package com.superzyen.page.soldier;

import com.superzyen.page.HomePage;
import com.superzyen.util.IoUtils;
import lombok.Data;

import java.io.IOException;

@Data
public class SoldierPage {

    private HomePage homePage = null;

    public void start(HomePage homePage) throws IOException {
        this.homePage = homePage;

        IoUtils.println("请输入你的选择");
        IoUtils.println("1 角色属性");
        IoUtils.println("2 装备");
        IoUtils.println("3 仓库");
        IoUtils.println("其他 返回上一页");
        int select = IoUtils.inputInt();
        switch (select) {
            case 1:
                new SoldierInfoPage().show(this);
                break;
            case 2:
                new WeaponPage().show(this);
                break;
            case 3:
                new WarehousePage().show(this);
                break;
            default:
                homePage.start();
                break;
        }

    }

}
