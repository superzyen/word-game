package com.superzyen.page.soldier;

import com.superzyen.dto.AccountDto;
import com.superzyen.util.AuthUtils;
import com.superzyen.service.weapon.Weapon;

import java.io.IOException;

/**
 * 装备
 */
public class WeaponPage {

    private Weapon weapon = new Weapon();

    public void show(SoldierPage soldierPage) throws IOException {
        AccountDto accountDto = AuthUtils.isLogin(soldierPage.getHomePage());
        weapon.setupInfo(accountDto);
        soldierPage.start(soldierPage.getHomePage());
    }
}
