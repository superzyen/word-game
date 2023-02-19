package com.superzyen.service.exchange;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.*;
import com.superzyen.domain.request.ExchangeRequest;
import com.superzyen.domain.vo.ExchangeListVO;
import com.superzyen.domain.vo.ResultVO;
import com.superzyen.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    ExchangeRecordMapper exchangeRecordMapper;
    @Autowired
    private WeaponInstanceMapper weaponInstanceMapper;

    @Autowired
    private WeaponMapper weaponMapper;
    @Autowired
    WalletMapper walletMapper;

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

    /**
     * 购买商品
     */
    @Transactional(rollbackFor = Exception.class)
    public String buy(ExchangeRequest request) {
        ResultVO resultVO = new ResultVO();

        //先检查是否有该商品
        List<Exchange> exchangeList = exchangeMapper.selectList(new QueryWrapper<Exchange>().eq("id", request.getExchangeId()));
        if (exchangeList.size() < 1) {
            resultVO.setIsSuccess(false);
            resultVO.setMessage("没有该商品！！");
            return JSON.toJSONString(resultVO);
        }

        Exchange exchange = exchangeList.get(0);
        List<WeaponInstance> weaponInstanceList = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>().eq("id", exchange.getInstanceId()));
        if (weaponInstanceList.size() < 1) {
            resultVO.setIsSuccess(false);
            resultVO.setMessage("没有该商品！！");
            return JSON.toJSONString(resultVO);
        }

        WeaponInstance instance = weaponInstanceList.get(0);
        Integer buyerId = request.getUserId();
        Integer sellerId = instance.getUserId();

        //检查买方余额是否足够
        List<Wallet> wallets = walletMapper.selectList(new QueryWrapper<Wallet>().eq("user_id",buyerId));
        List<Wallet> sellerWallets = walletMapper.selectList(new QueryWrapper<Wallet>().eq("user_id",sellerId));
        if(wallets.size()<1){
            resultVO.setIsSuccess(false);
            resultVO.setMessage("没有该买方！！");
            return JSON.toJSONString(resultVO);
        }
        Wallet buyerWallet = wallets.get(0);
        Wallet sellerWallet = sellerWallets.get(0);
        long buyerMoney = buyerWallet.getCurrency();
        long price = exchange.getPrice();
        if(buyerMoney<price){
            resultVO.setIsSuccess(false);
            resultVO.setMessage("余额不足！！");
            return JSON.toJSONString(resultVO);
        }

        //购买成功，更新实例所属id
        instance.setUserId(buyerId);
        weaponInstanceMapper.updateById(instance);

        //更新买卖方的余额
        buyerWallet.setCurrency((buyerWallet.getCurrency() - price));
        walletMapper.updateById(buyerWallet);
        sellerWallet.setCurrency((sellerWallet.getCurrency() + price));
        walletMapper.updateById(sellerWallet);

        //删掉该商品，并写入交易记录
        ExchangeRecord exchangeRecord = new ExchangeRecord()
                .setInstanceId(instance.getId())
                .setSellerId(sellerId)
                .setBuyerId(buyerId)
                .setCreateTime(request.getTime());
        exchangeRecordMapper.insert(exchangeRecord);

        exchangeMapper.deleteById(exchange);

        resultVO.setIsSuccess(true);
        resultVO.setMessage("购买了一件商品：" + instance.getId());
        return JSON.toJSONString(resultVO);

    }
}
