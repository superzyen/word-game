package com.superzyen.domain.request;

import lombok.Data;

import java.util.Date;

@Data
public class ExchangeRequest {
    private Integer exchangeId;
    private Integer userId;
    private Date time;
}
