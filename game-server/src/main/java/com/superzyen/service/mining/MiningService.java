package com.superzyen.service.mining;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.common.CommonSystemItem;
import com.superzyen.domain.entity.SystemSetting;
import com.superzyen.domain.entity.Wallet;
import com.superzyen.mapper.SystemSettingMapper;
import com.superzyen.mapper.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiningService {

    @Autowired
    SystemSettingMapper systemSettingMapper;
    @Autowired
    WalletMapper walletMapper;

    /**
     * 奖励游戏币
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean award(Integer userId) {
        SystemSetting systemSetting = systemSettingMapper.selectOne(new QueryWrapper<SystemSetting>().eq("item_name", CommonSystemItem.MONEY));
        Long dbMoney = Long.valueOf(systemSetting.getItemValue());
        Long finalMoney = dbMoney - 10L;
        if (finalMoney <= 0) {
            finalMoney = 0L;
        }
        systemSetting.setItemValue(String.valueOf(finalMoney));
        systemSettingMapper.updateById(systemSetting);

        //更新用户的钱包
        Wallet wallet = walletMapper.selectOne(new QueryWrapper<Wallet>().eq("user_id", userId));
        wallet.setCurrency(wallet.getCurrency() + 10);
        walletMapper.updateById(wallet);
        return true;
    }

    /**
     * 是否还有矿
     */
    public boolean remain() {
        SystemSetting systemSetting = systemSettingMapper.selectOne(new QueryWrapper<SystemSetting>().eq("item_name", CommonSystemItem.MONEY));
        Long dbMoney = Long.valueOf(systemSetting.getItemValue());
        if (dbMoney > 0) {
            return true;
        } else {
            return false;
        }
    }
}
