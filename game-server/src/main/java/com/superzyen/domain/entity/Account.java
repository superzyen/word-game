package com.superzyen.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账号
 *
 * @author wenzy
 * @date 2023/2/13
 **/


@Data
@Accessors(chain = true)
@TableName("account")
@KeySequence(value = "seq_user")
public class Account implements Serializable {
    private static final long serialVersionUID = -1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

}
