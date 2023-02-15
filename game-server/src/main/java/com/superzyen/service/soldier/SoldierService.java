package com.superzyen.service.soldier;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Soldier;
import com.superzyen.mapper.SoldierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldierService {

    @Autowired
    SoldierMapper soldierMapper;

    public String info(Integer userId) {
        Soldier soldier = soldierMapper.selectOne(new QueryWrapper<Soldier>().eq("user_id", userId));
        return JSON.toJSONString(soldier);
    }
}
