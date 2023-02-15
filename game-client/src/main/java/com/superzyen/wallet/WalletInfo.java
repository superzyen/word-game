package com.superzyen.wallet;

import com.alibaba.fastjson.JSONObject;
import com.superzyen.common.ServerSetting;
import com.superzyen.dto.AccountDto;
import com.superzyen.dto.WalletDto;
import com.superzyen.page.HomePage;
import com.superzyen.util.HttpUtils;
import com.superzyen.util.IoUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Slf4j
@Data
public class WalletInfo {

    public void show() throws IOException {
        //先查询用户信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", HomePage.getGlobalStatus().getName());
        jsonObject.put("password", HomePage.getGlobalStatus().getPassword());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/userInfo", body);
        AccountDto accountDto = null;

        if (StringUtils.isNotBlank(res)) {
            accountDto = JSONObject.parseObject(res, AccountDto.class);
            if (null == accountDto) {
                IoUtils.println("查询失败");
            }

        }

        //查询钱包信息
        if (null != accountDto) {
            jsonObject = new JSONObject();
            jsonObject.put("userId", accountDto.getId());
            body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
            res = HttpUtils.post(ServerSetting.getPath() + "/server/show", body);
        }

        if (StringUtils.isNotBlank(res)) {
            WalletDto walletDto = JSONObject.parseObject(res, WalletDto.class);
            if (null == walletDto) {

                IoUtils.println("查询失败");
            }
            IoUtils.println("游戏币：【" + walletDto.getCurrency() + "】");
            IoUtils.println("输入回车继续...");
            IoUtils.inputString();
            return;
        }
        IoUtils.println("查询失败");
        return;
    }
}
