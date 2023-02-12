package com.superzyen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superzyen.domain.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
