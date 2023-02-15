package com.superzyen.Soldier;

import com.superzyen.dto.SoldierDto;
import com.superzyen.util.CommonApiUtils;
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
    }
}
