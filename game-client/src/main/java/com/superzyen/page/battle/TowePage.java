package com.superzyen.page.battle;

import com.superzyen.dto.AccountDto;
import com.superzyen.service.tower.Tower;
import com.superzyen.util.AuthUtils;
import com.superzyen.util.IoUtils;
import com.superzyen.vo.CurrentTowerVO;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class TowePage {

    private Tower tower = new Tower();

    public void climb(BattlePage battlePage) throws IOException {
        AccountDto accountDto = AuthUtils.isLogin(battlePage.getHomePage());

        boolean isEscape = false;
        while (true){
            if(isEscape){
                battlePage.start(battlePage.getHomePage());
                break;
            }
            CurrentTowerVO currentTowerVo = this.tower.currentTowerInfo(accountDto.getId());

            if(StringUtils.isBlank(currentTowerVo.getMonsterName())){
                isEscape =true;
                continue;
            }

            IoUtils.println("1 攻击BOSS");
            IoUtils.println("2 返回上一页");
            IoUtils.println("请输入你的选择：");
            int select = IoUtils.inputInt();
            switch (select) {
                case 1:
                    this.tower.attack(accountDto.getId(), currentTowerVo);
                    break;
                default:
                    isEscape = true;
                    break;
            }
        }
    }


}
