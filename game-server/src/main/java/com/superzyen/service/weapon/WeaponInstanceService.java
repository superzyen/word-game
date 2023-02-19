package com.superzyen.service.weapon;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.common.CommonSystemItem;
import com.superzyen.domain.entity.*;
import com.superzyen.domain.vo.ResultVO;
import com.superzyen.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WeaponInstanceService {

    @Autowired
    private WeaponInstanceMapper weaponInstanceMapper;
    @Autowired
    private WeaponMapper weaponMapper;
    @Autowired
    private ExchangeMapper exchangeMapper;
    @Autowired
    WalletMapper walletMapper;
    @Autowired
    SystemSettingMapper systemSettingMapper;

    @Transactional(rollbackFor = Exception.class)
    public String setup(Integer instanceId, Integer userId) {

        //先检查是否拥有该实例装备
        List<WeaponInstance> dbInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>()
                .eq("id", instanceId).eq("user_id", userId));

        if (dbInstanceList.size() <= 0) {
            return "false";
        }

        List<WeaponInstance> dbSetupedList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>()
                .eq("user_id", userId).eq("is_setup", true));

        //将新实例改为装备状态，旧实例取消装备状态
        WeaponInstance newInstance = dbInstanceList.get(0);
        newInstance.setIsSetup(true);

        if(dbSetupedList.size()>0){
            WeaponInstance oldInstance = dbSetupedList.get(0);
            oldInstance.setIsSetup(false);
            weaponInstanceMapper.updateById(oldInstance);
        }

        weaponInstanceMapper.updateById(newInstance);
        return "true";
    }

    /**
     * 出售实例
     */
    @Transactional(rollbackFor = Exception.class)
    public String sell(Integer instanceId, Integer userId,Long price) {
        ResultVO resultVo =new ResultVO();

        //先检查是否拥有该实例装备
        List<WeaponInstance> dbInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>()
                .eq("id", instanceId).eq("user_id", userId));

        if (dbInstanceList.size() <= 0) {
            resultVo.setIsSuccess(false);
            resultVo.setMessage("未拥有该装备！！");
            return JSON.toJSONString(resultVo);
        }

        //检查是否装备状态
        if (dbInstanceList.get(0).getIsSetup()) {

            resultVo.setIsSuccess(false);
            resultVo.setMessage("装备状态的装备不能出售");
            return JSON.toJSONString(resultVo);
        }

        WeaponInstance weaponInstance = dbInstanceList.get(0);

        //是否可卖
        List<Weapon> weapons = weaponMapper.selectList(new QueryWrapper<Weapon>().eq("id", weaponInstance.getWeaponId())
                .eq("issell", true));
        if (weapons.size() <= 0) {
            resultVo.setIsSuccess(false);
            resultVo.setMessage("该装备不可卖！！");
            return JSON.toJSONString(resultVo);
        }

        //是否已经上架
        List<Exchange> exchangeList = exchangeMapper.selectList(new QueryWrapper<Exchange>().eq("instance_id", weaponInstance.getId()));
        if (exchangeList.size() > 0) {
            resultVo.setIsSuccess(false);
            resultVo.setMessage("不可重复上架！！");
            return JSON.toJSONString(resultVo);
        }

        //检查卖主是否有足够的手续费
        //手续费为价格的百分之5，不足1为1，小数四舍五入
        Long serviceCharge = getServiceCharge(price);
        boolean isSuccess = operateServiceCharge(serviceCharge, userId);
        if (!isSuccess) {

            resultVo.setIsSuccess(false);
            resultVo.setMessage("手续费不足！！");
            return JSON.toJSONString(resultVo);
        }

        Exchange exchange = new Exchange()
                .setInstanceId(weaponInstance.getId())
                .setPrice(price)
                .setCreateTime(new Date());
        exchangeMapper.insert(exchange);

        resultVo.setIsSuccess(true);
        resultVo.setMessage("上架惠交所成功。");
        return JSON.toJSONString(resultVo);
    }

    /**
     * 扣手续费操作
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean operateServiceCharge(Long serviceCharge, Integer userId) {

        //查询卖家余额
        List<Wallet> wallets = walletMapper.selectList(new QueryWrapper<Wallet>().eq("user_id",userId));
        if(wallets.size()<1){
            return false;
        }

        Long currency = wallets.get(0).getCurrency();
        if (currency < serviceCharge) {
            //余额不足则无法出售装备
            return false;
        } else {
            //余额足够则扣取卖家的余额
            Wallet wallet = wallets.get(0);
            currency = currency - serviceCharge;
            wallet.setCurrency(currency);
            walletMapper.updateById(wallet);
        }

        //手续费更新到矿库
        List<SystemSetting> systemSettings = systemSettingMapper.selectList(new QueryWrapper<SystemSetting>().eq("item_name", CommonSystemItem.MONEY));
        SystemSetting systemSetting = systemSettings.get(0);
        Long money = Long.valueOf(systemSetting.getItemValue());
        money = money + serviceCharge;
        systemSetting.setItemValue(String.valueOf(money));
        systemSettingMapper.updateById(systemSetting);


        return true;

    }


    /**
     *  计算手续费
     *  手续费为价格的百分之5，不足1为1，小数四舍五入
     */
    private Long getServiceCharge(Long price) {
        BigDecimal priceMal = new BigDecimal(price);
        BigDecimal serviceMal = priceMal.multiply(new BigDecimal(0.05)).setScale(0, BigDecimal.ROUND_DOWN);
        Long serviceCharge = serviceMal.longValue();
        if (serviceCharge < 1L) {
            return 1L;
        }
        return serviceCharge;
    }
}
