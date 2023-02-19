package com.superzyen.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultVO {

    private Boolean isSuccess;
    private String message;
}
