package com.superzyen.service.account;

import com.superzyen.common.ServerSetting;
import com.superzyen.util.HttpUtils;
import com.superzyen.util.IoUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Server {

    public boolean isStarted() throws IOException {

       try{
           String res = HttpUtils.get(ServerSetting.getPath() + "/server/zyen/v1");

           if(StringUtils.isNotBlank(res)){
               return true;
           }
           return false;
       }catch (RuntimeException runtimeException){
           IoUtils.println("服务器未启动，请联系作者阿骨。");
           throw runtimeException;
       }
    }
}
