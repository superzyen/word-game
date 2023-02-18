package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 装备
 */

@Data
@Accessors(chain = true)
@TableName("weapon")
@KeySequence(value = "seq_weapon")
public class Weapon implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField("name")
    private String name;

    /**
     * 效果介绍
     */
    @TableField("intro")
    private String intro;

    /**
     * 所属类名
     */
    @TableField("belong_clazz")
    private String belongClazz;

    /**
     * 是否可售
     */
    @TableField("isSell")
    private Boolean isSell;

    /**
     * 全服数量
     */
    @TableField("server_qty")
    private Integer serverQty;

    /**
     * 使用类型
     * 1. 无限次， 2. 单次， 3. 指定次数， 4. 冷却时间
     */
    @TableField("usage_type")
    private Integer usageType;

    /**
     * 配合使用类型
     * 指定次数
     */
    @TableField("usage_num")
    private Integer usageNum;

    /**
     * 配合使用类型
     * 冷却时间，单位 天
     */
    @TableField("frozen_day")
    private Integer frozenDay;

}
