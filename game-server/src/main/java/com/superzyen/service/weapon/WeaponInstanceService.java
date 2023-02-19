package com.superzyen.service.weapon;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Exchange;
import com.superzyen.domain.entity.Weapon;
import com.superzyen.domain.entity.WeaponInstance;
import com.superzyen.mapper.ExchangeMapper;
import com.superzyen.mapper.WeaponInstanceMapper;
import com.superzyen.mapper.WeaponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String sell(Integer instanceId, Integer userId) {

        //先检查是否拥有该实例装备
        List<WeaponInstance> dbInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>()
                .eq("id", instanceId).eq("user_id", userId));

        if (dbInstanceList.size() <= 0) {
            return "false";
        }

        WeaponInstance weaponInstance = dbInstanceList.get(0);

        //是否可卖
        List<Weapon> weapons = weaponMapper.selectList(new QueryWrapper<Weapon>().eq("id", weaponInstance.getWeaponId())
                .eq("issell", true));
        if (weapons.size() <= 0) {
            return "false";
        }

        //是否已经上架
        List<Exchange> exchangeList = exchangeMapper.selectList(new QueryWrapper<Exchange>().eq("instance_id", weaponInstance.getId()));
        if (exchangeList.size() > 0) {
            return "false";
        }

        Exchange exchange = new Exchange()
                .setInstanceId(weaponInstance.getId())
                .setCreateTime(new Date());
        exchangeMapper.insert(exchange);
        return "true";
    }
}
