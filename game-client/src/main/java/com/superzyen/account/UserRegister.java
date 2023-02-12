package com.superzyen.account;

import com.alibaba.fastjson.JSONObject;
import com.superzyen.common.ServerSetting;
import com.superzyen.util.CryptoUtils;
import com.superzyen.util.HttpUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;

public class UserRegister {

    /**
     * 账号密码注册
     */
    public boolean register(String name, String password) throws IOException {
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
