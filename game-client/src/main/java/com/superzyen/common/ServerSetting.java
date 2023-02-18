package com.superzyen.common;

import lombok.Data;

@Data
public class ServerSetting {

    private final static String protocal = "http://";
    private final static String ip = "superzyen.gnway.cc";
    private final static String localhost = "127.0.0.1:8080";
    private final static String port = "8080";

    public static String getPath() {
        return protocal + localhost;
    }
}
