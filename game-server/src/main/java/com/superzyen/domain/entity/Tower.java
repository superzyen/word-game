package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * 常规塔系
 */
@Data
@Accessors(chain = true)
@TableName("tower")
@KeySequence(value = "seq_tower")
public class Tower implements Serializable {

    private static final long serialVersionUID = -1L;


    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    /**
     * BOSS id
     */
    @TableField("monster_id")
    private Integer monsterId;

    /**
     * 塔层
     */
    @TableField("tower_level")
    private Integer towerLevel;

    @TableField("create_time")
    private Date createTime;
}
