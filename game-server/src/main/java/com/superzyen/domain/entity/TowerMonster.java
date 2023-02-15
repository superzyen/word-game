package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  塔系BOSS
 */

@Data
@Accessors(chain = true)
@TableName("tower_monster")
@KeySequence(value = "seq_tower_monster")
public class TowerMonster implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     *  NORMAL 普通爆率， EASY 简单, HARD 困难
     */
    public static final String AWARD_TYPE_NORMAL="NORMAL";
    public static final String AWARD_TYPE_EASY="EASY";
    public static final String AWARD_TYPE_HARD="HARD";

    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    @TableField("name")
    private String name;

    /**
     *  简介
     */
    @TableField("intro")
    private String intro;

    /**
     * 攻击力
     */
    @TableField("atk")
    private Double atk;

    /**
     * 防御力
     */
    @TableField("def")
    private Double def;

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
     *  第一个击败的人
     */
    @TableField("first_pass_user")
    private Integer firstPassUser;

    /**
     *  塔层
     */
    @TableField("tower_level")
    private Integer towerLevel;

    /**
     * 奖励武器
     */
    @TableField("weapon_id")
    private Integer weaponId;

    /**
     * 奖励的爆率类型
     */
    @TableField("award_type")
    private String awardType;



}
