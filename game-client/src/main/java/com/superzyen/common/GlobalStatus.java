package com.superzyen.common;

import lombok.Data;

@Data
public class GlobalStatus {

    private boolean isLogined = false;
    private String name = "";
    /**
     * 加密后密码
     */
    private String password = "";
}
