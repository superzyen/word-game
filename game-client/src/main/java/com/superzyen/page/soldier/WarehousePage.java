package com.superzyen.page.soldier;

import com.superzyen.dto.AccountDto;
import com.superzyen.service.weapon.Warehouse;
import com.superzyen.util.AuthUtils;

import java.io.IOException;

/**
 * 仓库
 */
public class WarehousePage {

    private Warehouse warehouse = new Warehouse();

    public void show(SoldierPage soldierPage) throws IOException {

        AccountDto accountDto = AuthUtils.isLogin(soldierPage.getHomePage());
        warehouse.show(accountDto);
        soldierPage.start(soldierPage.getHomePage());
    }
}
