package com.superzyen.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 战斗角色
 *
 * @author wenzy
 * @date 2023/2/13
 **/


@Data
@Accessors(chain = true)
public class SoldierDto implements Serializable {
    private static final long serialVersionUID = -1L;
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 基础攻击
     */
    private Double basicAtk;

    /**
     * 基础防御
     */
    private Double basicDef;

    /**
     * 血量
     */
    private Double hp;

    /**
     * 攻速
     */
    private Integer speed;

    /**
     * 暴击率
     */
    private Double critRate;

    /**
     * 未来成长倍率
     */
    private Double futureRate;
}
