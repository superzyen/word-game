package com.superzyen.service.account;

import com.superzyen.domain.entity.Account;
import com.superzyen.mapper.AccountMapper;
import com.superzyen.util.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
