package com.superzyen.service.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Account;
import com.superzyen.mapper.AccountMapper;
import com.superzyen.util.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    AccountMapper accountMapper;

    public boolean register(String name, String password) {
        //先解密
        String originalPwd=CryptoUtils.decrypt(password);
        Account account = new Account()
                .setName(name)
                .setPassword(originalPwd);
        accountMapper.insert(account);
        return true;
    }

    public boolean canRegister(String name) {
        //先解密
        List<Account> dbAccount = accountMapper.selectList(new QueryWrapper<Account>().eq("name", name));
        if (dbAccount.size() > 0) {
            return false;
        }
        return true;
    }
}
