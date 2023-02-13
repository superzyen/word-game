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
     * 攻速
     */
    @TableField("speed")
    private Integer speed;

    /**
     *  第一个击败的人
     */
    @TableField("first_pass_user")
    private Integer firstPassUser;



}
