package com.superzyen.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WarehouseVO {
    private Integer instanceId;
    private String name;
    private String intro;
    private Boolean isSetup;
}
