package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("exchange_record")
@KeySequence(value = "seq_exchange_record")
public class ExchangeRecord implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField("instance_id")
    private Integer instanceId;

    @TableField("seller_id")
    private Integer sellerId;

    @TableField("buyer_id")
    private Integer buyerId;

    @TableField("create_time")
    private Date createTime;
}
