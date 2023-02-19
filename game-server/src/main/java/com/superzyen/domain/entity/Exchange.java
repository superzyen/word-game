package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("exchange")
@KeySequence(value = "seq_exchange")
public class Exchange implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField("instance_id")
    private Integer instanceId;

    @TableField("create_time")
    private Date createTime;

    @TableField("price")
    private Long price;
}
