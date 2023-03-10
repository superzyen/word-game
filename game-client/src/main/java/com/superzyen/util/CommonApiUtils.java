package com.superzyen.util;

import com.alibaba.fastjson.JSONObject;
import com.superzyen.common.ServerSetting;
import com.superzyen.dto.AccountDto;
import com.superzyen.dto.SoldierDto;
import com.superzyen.page.HomePage;
import com.superzyen.vo.*;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

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

    /**
     *  获取角色基础信息
     */
    public static SoldierDto soldierInfo(Integer userId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/soldier/info", body);
        return JSONObject.parseObject(res, SoldierDto.class);
    }

    /**
     * 判断该账号是否可以注册
     */
    public static Boolean canRegister(String name) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/canRegister", body);
        return Boolean.valueOf(res);
    }

    /**
     * 装备信息
     */
    public static SoldierSetupVO setupInfo(Integer userId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/weapon/setup/info", body);
        return JSONObject.parseObject(res, SoldierSetupVO.class);
    }

    /**
     * 仓库列表
     */
    public static List<WarehouseVO> warehouse(Integer userId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/weapon/warehouse", body);
        return JSONObject.parseArray(res, WarehouseVO.class);
    }

    /**
     * 交易所商品列表
     */
    public static List<ExchangeListVO> exchangeList() throws IOException {
        JSONObject jsonObject = new JSONObject();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/exchange/list", body);
        return JSONObject.parseArray(res, ExchangeListVO.class);
    }

    /**
     * 装备实例
     */
    public static Boolean setupInstance(Integer userId, Integer instanceId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("instanceId", instanceId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/weaponinstance/setup", body);
        return Boolean.valueOf(res);
    }

    /**
     * 出售实例
     */
    public static ResultVO sellInstance(Integer userId, Integer instanceId, long price) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("instanceId", instanceId);
        jsonObject.put("price", price);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/weaponinstance/sell", body);
        return JSONObject.parseObject(res, ResultVO.class);
    }

    /**
     * 获取当前塔层详情
     */
    public static CurrentTowerVO towerInfo(Integer userId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/tower/show", body);
        return JSONObject.parseObject(res, CurrentTowerVO.class);
    }

    /**
     * 进攻塔系BOSS
     */
    public static TowerResultVO towerAttack(Integer userId, Integer monsterId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("monsterId", monsterId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/tower/attack", body);
        return JSONObject.parseObject(res, TowerResultVO.class);
    }

    /**
     *  购买装备实例
     */
    public static ResultVO buyInstance(Integer exhangeId, Integer userId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("exchangeId", exhangeId);
        jsonObject.put("userId", userId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());
        String res = HttpUtils.post(ServerSetting.getPath() + "/server/exchange/buy", body);
        return JSONObject.parseObject(res, ResultVO.class);
    }
}
