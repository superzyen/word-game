package com.superzyen.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SoldierSetupVO {

    private Integer instanceId;
    private String name;
    private String intro;
}
