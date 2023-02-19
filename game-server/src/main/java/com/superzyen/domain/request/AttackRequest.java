package com.superzyen.domain.request;

import lombok.Data;

import java.util.Date;

@Data
public class AttackRequest {
    private Integer userId;
    private Integer monsterId;
    private Date time;
}
