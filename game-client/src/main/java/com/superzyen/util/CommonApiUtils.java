package com.superzyen.util;

import com.alibaba.fastjson.JSONObject;
import com.superzyen.common.ServerSetting;
import com.superzyen.dto.AccountDto;
import com.superzyen.page.HomePage;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class CommonApiUtils {

    public static AccountDto userInfo() throws IOException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", HomePage.getGlobalStatus().getName());
        jsonObject.put("password", HomePage.getGlobalStatus().getPassword());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/userInfo", body);

        if (StringUtils.isNotBlank(res)) {
            return JSONObject.parseObject(res, AccountDto.class);
        }
        return null;
    }

    public static Boolean award(Integer userId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/award", body);
        return Boolean.valueOf(res);
    }
}
