package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("soldier")
@KeySequence(value = "seq_soldier")
public class Soldier implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 基础攻击
     */
    @TableField("basic_atk")
    private Double basicAtk;

    /**
     * 基础防御
     */
    @TableField("basic_def")
    private Double basicDef;

    /**
     * 血量
     */
    @TableField("hp")
    private Double hp;

    /**
     * 攻速
     */
    @TableField("speed")
    private Integer speed;

    /**
     * 暴击率
     */
    @TableField("crit_rate")
    private Double critRate;

    /**
     * 未来成长倍率
     */
    @TableField("future_rate")
    private Double futureRate;
}
