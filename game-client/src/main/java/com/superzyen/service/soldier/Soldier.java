package com.superzyen.service.soldier;

import com.superzyen.dto.SoldierDto;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Data
@Slf4j
public class Soldier {


    /**
     * 个人信息
     */
    public void soldierInfo(Integer userId) throws IOException {
        SoldierDto soldierDto = CommonApiUtils.soldierInfo(userId);
        IoUtils.println("=========================================");
        IoUtils.println("基础攻击："+soldierDto.getBasicAtk());
        IoUtils.println("基础防御："+soldierDto.getBasicDef());
        IoUtils.println("基础血量："+soldierDto.getHp());
        IoUtils.println("基础攻速："+soldierDto.getSpeed());
        IoUtils.println("未来成长倍率："+soldierDto.getFutureRate());
        IoUtils.println("暴击效果："+soldierDto.getCritEffect());
        IoUtils.println("=========================================");
    }
}
