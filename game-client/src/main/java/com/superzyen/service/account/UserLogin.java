package com.superzyen.service.account;

import com.alibaba.fastjson.JSONObject;
import com.superzyen.common.ServerSetting;
import com.superzyen.page.HomePage;
import com.superzyen.util.CryptoUtils;
import com.superzyen.util.HttpUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;

public class UserLogin {

    /**
     * 账号密码登录验证
     */
    public boolean login(String name, String password) throws IOException {

        String cryptedPwd = CryptoUtils.encrypt(password);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", name);
        jsonObject.put("password", cryptedPwd);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/login", body);
        if (Boolean.valueOf(res)) {
            HomePage.getGlobalStatus().setName(name);
            HomePage.getGlobalStatus().setPassword(cryptedPwd);
            return true;
        }
        return false;
    }
}
