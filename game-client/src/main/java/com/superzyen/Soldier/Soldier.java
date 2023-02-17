package com.superzyen.Soldier;

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
    private void soldierInfo(Integer userId) throws IOException {
        SoldierDto soldierDto = CommonApiUtils.soldierInfo(userId);
        IoUtils.println("=========================================");
        IoUtils.println("基础攻击："+soldierDto.getBasicAtk());
        IoUtils.println("基础防御："+soldierDto.getBasicDef());
        IoUtils.println("基础攻击："+soldierDto.getHp());
        IoUtils.println("=========================================");
    }
}
