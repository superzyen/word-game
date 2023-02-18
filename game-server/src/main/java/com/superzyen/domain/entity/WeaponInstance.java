package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 装备实例
 */
@Data
@Accessors(chain = true)
@TableName("weapon_instance")
@KeySequence(value = "seq_weapon_instance")
public class WeaponInstance implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 装备模型id
     */
    @TableField("weapon_id")
    private Integer weaponId;

    /**
     * 所属用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 是否装备
     */
    @TableField("is_setup")
    private Boolean isSetup;

    /**
     * 每日是否使用
     */
    @TableField("daily_is_usage")
    private Boolean dailyIsUsage;

    /**
     * 每日使用次数
     */
    @TableField("daily_usage_num")
    private Integer dailyUsageNum;
}
