package com.superzyen.service.exchange;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Exchange;
import com.superzyen.domain.entity.Weapon;
import com.superzyen.domain.entity.WeaponInstance;
import com.superzyen.domain.vo.ExchangeListVO;
import com.superzyen.mapper.ExchangeMapper;
import com.superzyen.mapper.WeaponInstanceMapper;
import com.superzyen.mapper.WeaponMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;
    @Autowired
    private WeaponInstanceMapper weaponInstanceMapper;

    @Autowired
    private WeaponMapper weaponMapper;

    public String list() {
        //查询惠交所的上架记录
        List<Exchange> exchangeList = exchangeMapper.selectList(new QueryWrapper<Exchange>());
        List<Integer> instanceIds = exchangeList.stream().map(x -> x.getInstanceId()).collect(Collectors.toList());

        if (instanceIds.size() <= 0) {
            return null;
        }

        //实例列表
        List<WeaponInstance> weaponInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>().in("id", instanceIds));
        List<Integer> weaponIds = weaponInstanceList.stream().map(x -> x.getWeaponId()).distinct().collect(Collectors.toList());
        Map<Integer, WeaponInstance> idInstanceMap = weaponInstanceList.stream().collect(Collectors.toMap(WeaponInstance::getId, Function.identity(), (k1, k2) -> k1));

        //获取装备模型列表
        List<Weapon> weapons = weaponMapper.selectList(new QueryWrapper<Weapon>().in("id", weaponIds));
        Map<Integer, Weapon> idWeaponMap = weapons.stream().collect(Collectors.toMap(Weapon::getId, Function.identity(), (k1, k2) -> k1));

        //组装列表
        List<ExchangeListVO> results = new ArrayList<>();
        ExchangeListVO tmpVO = null;
        for (Exchange exchange : exchangeList) {
            tmpVO = new ExchangeListVO();

            tmpVO.setId(exchange.getId());
            tmpVO.setInstanceId(exchange.getInstanceId());

            WeaponInstance weaponInstance = null;
            if (idInstanceMap.containsKey(exchange.getInstanceId())) {
                weaponInstance = idInstanceMap.get(exchange.getInstanceId());
                tmpVO.setWeaponId(weaponInstance.getWeaponId());
                tmpVO.setOwnerId(weaponInstance.getUserId());
            }

            if (idWeaponMap.containsKey(weaponInstance.getWeaponId())) {
                Weapon weapon = idWeaponMap.get(weaponInstance.getWeaponId());
                tmpVO.setName(weapon.getName());
                tmpVO.setIntro(weapon.getIntro());
                tmpVO.setServerQty(weapon.getServerQty());
                tmpVO.setUsageType(weapon.getUsageType());
                tmpVO.setUsageNum(weapon.getUsageNum());
                tmpVO.setFrozenDay(weapon.getFrozenDay());
                tmpVO.setType(weapon.getType());
            }

            results.add(tmpVO);
        }
        return JSON.toJSONString(results);
    }
}
