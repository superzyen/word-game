package com.superzyen.common;

import lombok.Data;

@Data
public class GlobalStatus {

    private boolean isLogined = false;
    private String name = "";
    private String password = "";
}
