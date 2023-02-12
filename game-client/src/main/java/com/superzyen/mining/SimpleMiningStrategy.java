package com.superzyen.mining;

import com.superzyen.util.IoUtils;

/**
 * 一种简单的概率挖矿策略，后期可更换
 */
public class SimpleMiningStrategy implements IDefaultMiningStrategy {
    @Override
    public boolean calculate() {

        Long result = (long) (Math.random() * 1000000000L) + 1;
        if (result % 99998800L == 0 || result % 69998800L == 0 || result % 39998800L == 0 || result % 19998800L == 0) {
            return true;
        }

        return false;
    }
}
