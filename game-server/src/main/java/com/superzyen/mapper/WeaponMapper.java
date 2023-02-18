package com.superzyen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superzyen.domain.entity.Wallet;
import com.superzyen.domain.entity.Weapon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeaponMapper extends BaseMapper<Weapon> {
}
