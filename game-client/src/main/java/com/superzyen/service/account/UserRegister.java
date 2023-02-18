package com.superzyen.service.account;

import com.alibaba.fastjson.JSONObject;
import com.superzyen.common.ServerSetting;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.CryptoUtils;
import com.superzyen.util.HttpUtils;
import com.superzyen.util.IoUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;

public class UserRegister {

    /**
     * 账号密码注册
     */
    public boolean register(String name, String password) throws IOException {
        //判断账号是否已经被使用
        boolean isPromisson = CommonApiUtils.canRegister(name);
        if (!isPromisson) {
            IoUtils.println("该账号已被使用！");
            return false;
        }


        String cryptedPwd = CryptoUtils.encrypt(password);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("password", cryptedPwd);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/register", body);
        if (Boolean.valueOf(res)) {
            return true;
        }
        return false;
    }
}
