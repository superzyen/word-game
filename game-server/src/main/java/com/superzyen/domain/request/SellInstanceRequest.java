package com.superzyen.domain.request;

import lombok.Data;

@Data
public class SellInstanceRequest {
    private Integer instanceId;
    private Integer userId;
    private Long price;
}
