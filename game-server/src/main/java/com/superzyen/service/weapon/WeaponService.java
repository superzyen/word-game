package com.superzyen.service.weapon;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Weapon;
import com.superzyen.domain.entity.WeaponInstance;
import com.superzyen.domain.vo.SoldierSetupVO;
import com.superzyen.domain.vo.WarehouseVO;
import com.superzyen.mapper.WeaponInstanceMapper;
import com.superzyen.mapper.WeaponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WeaponService {

    @Autowired
    private WeaponInstanceMapper weaponInstanceMapper;
    @Autowired
    private WeaponMapper weaponMapper;

    public String setupInfo(Integer userId) {
        List<WeaponInstance> weaponInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>()
                .eq("user_id", userId).eq("is_setup", true));
        if (weaponInstanceList.size() <= 0) {
            return null;
        }
        Integer weaponId = weaponInstanceList.get(0).getWeaponId();
        Weapon weapon = weaponMapper.selectById(weaponId);
        SoldierSetupVO soldierSetupVO = new SoldierSetupVO()
                .setInstanceId(weaponInstanceList.get(0).getId())
                .setName(weapon.getName())
                .setIntro(weapon.getIntro());

        return JSON.toJSONString(soldierSetupVO);
    }

    public String warehouse(Integer userId) {
        List<WeaponInstance> weaponInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>()
                .eq("user_id", userId));
        if (weaponInstanceList.size() <= 0) {
            return null;
        }
        List<Integer> weaponIds = weaponInstanceList.stream().map(x -> x.getWeaponId()).distinct().collect(Collectors.toList());
        List<Weapon> weapons = weaponMapper.selectList(new QueryWrapper<Weapon>().in("id", weaponIds));
        Map<Integer, Weapon> idObjMap = weapons.stream().collect(Collectors.toMap(Weapon::getId, Function.identity(), (k1, k2) -> k1));

        List<WarehouseVO> warehouseVOList = new ArrayList<>();
        for (WeaponInstance instance : weaponInstanceList) {
            Integer weaponId = instance.getWeaponId();

            if (idObjMap.containsKey(weaponId)) {
                Weapon tmp = idObjMap.get(weaponId);
                WarehouseVO warehouseVO = new WarehouseVO()
                        .setInstanceId(instance.getId())
                        .setName(tmp.getName())
                        .setIntro(tmp.getIntro())
                        .setIsSetup(instance.getIsSetup());
                warehouseVOList.add(warehouseVO);
            }
        }

        return JSON.toJSONString(warehouseVOList);
    }
}
