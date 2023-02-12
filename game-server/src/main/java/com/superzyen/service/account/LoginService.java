package com.superzyen.service.account;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Account;
import com.superzyen.mapper.AccountMapper;
import com.superzyen.util.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    AccountMapper accountMapper;

    public boolean login(String name, String password) {
        String originalPwd = CryptoUtils.decrypt(password);
        List<Account> accountList = accountMapper.selectList(new QueryWrapper<Account>().eq("name", name).eq("password", originalPwd));
        if (accountList.size() > 0) {
            return true;
        }
        return false;
    }

    public String userInfo(String name, String password) {
        String originalPwd = CryptoUtils.decrypt(password);
        List<Account> accountList = accountMapper.selectList(new QueryWrapper<Account>().eq("name", name).eq("password", originalPwd));
        if (accountList.size() > 0) {
            Account account = accountList.get(0);
            return JSON.toJSONString(account);
        }
        return null;
    }

}
