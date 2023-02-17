package com.superzyen.page;

import com.superzyen.Soldier.Soldier;

public class SoldierPage {

    Soldier soldier = null;

    public void show(HomePage homePage) {
        if (soldier == null) {
            soldier = new Soldier();
        }
    }

}
