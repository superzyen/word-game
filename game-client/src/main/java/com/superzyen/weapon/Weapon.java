package com.superzyen.weapon;

import com.superzyen.dto.AccountDto;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;
import com.superzyen.vo.SoldierSetupVO;

import java.io.IOException;

public class Weapon {

    public void setupInfo(AccountDto accountDto) throws IOException {

        SoldierSetupVO soldierSetupVO = CommonApiUtils.setupInfo(accountDto.getId());
        if(null==soldierSetupVO){
            IoUtils.println("暂无装备");
            return;
        }

        IoUtils.println("=========================================");
        IoUtils.println("装备唯一码：" + soldierSetupVO.getInstanceId());
        IoUtils.println("装备名：" + soldierSetupVO.getName());
        IoUtils.println("装备效果说明：" + soldierSetupVO.getIntro());
        IoUtils.println("=========================================");
        return;
    }
}
