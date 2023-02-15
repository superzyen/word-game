package com.superzyen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superzyen.domain.entity.Soldier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SoldierMapper extends BaseMapper<Soldier> {
}
