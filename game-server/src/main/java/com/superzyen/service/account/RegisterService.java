package com.superzyen.service.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.Account;
import com.superzyen.domain.entity.Soldier;
import com.superzyen.domain.entity.Wallet;
import com.superzyen.mapper.AccountMapper;
import com.superzyen.mapper.SoldierMapper;
import com.superzyen.mapper.WalletMapper;
import com.superzyen.util.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    WalletMapper walletMapper;
    @Autowired
    SoldierMapper soldierMapper;

    public boolean register(String name, String password) {
        //先解密
        String originalPwd=CryptoUtils.decrypt(password);
        Account account = new Account()
                .setName(name)
                .setPassword(originalPwd);
        accountMapper.insert(account);

        //同时生成钱包账号
        Wallet wallet = new Wallet()
                .setCurrency(0L)
                .setUserId(account.getId());
        walletMapper.insert(wallet);

        //同时生成战斗角色
        Soldier soldier = this.initSoldier(account.getId());
        soldierMapper.insert(soldier);

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

    private Soldier initSoldier(Integer userId) {
        return new Soldier()
                .setUserId(userId)
                .setBasicAtk(10D)
                .setBasicDef(10D)
                .setHp(100D)
                .setSpeed(1)
                .setCritEffect(100D)
                .setFutureRate(1D);
    }
}
