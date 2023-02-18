package com.superzyen.page.soldier;

import com.superzyen.service.soldier.Soldier;
import com.superzyen.dto.AccountDto;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;

import java.io.IOException;

/**
 * 战斗角色信息
 */
public class SoldierInfoPage {

    Soldier soldier = null;

    public void show(SoldierPage soldierPage) throws IOException {
        if (soldier == null) {
            soldier = new Soldier();
        }
        AccountDto accountDto = CommonApiUtils.userInfo();
        if (null == accountDto) {
            IoUtils.println("请先登录");
            soldierPage.getHomePage().start();
            return;
        }
        soldier.soldierInfo(accountDto.getId());
        soldierPage.start(soldierPage.getHomePage());
    }

}
