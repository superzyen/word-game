package com.superzyen.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TowerResultVO {

    private Boolean isSuccess;
    private String message;
    /**
     * BOSS奖励
     */
    private Boolean isAward;
}
