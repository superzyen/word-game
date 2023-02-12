package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("system_setting")
@KeySequence(value = "seq_system_setting")
public class SystemSetting implements Serializable {

    private static final long serialVersionUID = -1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField("item_name")
    private String itemName;

    @TableField("item_value")
    private String itemValue;
}
