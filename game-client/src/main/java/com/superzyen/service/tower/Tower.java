package com.superzyen.service.tower;

import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;
import com.superzyen.vo.CurrentTowerVO;
import com.superzyen.vo.TowerResultVO;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Tower {

    private static final int PASS_LEVEL = 1;


    public CurrentTowerVO currentTowerInfo(Integer userId) throws IOException {
        IoUtils.println("欢迎来到常规塔系。");
        IoUtils.println("这里存在前所未有的强大BOSS！");
        IoUtils.println("击败BOSS还有机会获得稀有装备，尽情变得强大！");
        IoUtils.println("=========================================");
        //查看当前用户所在塔层
        CurrentTowerVO currentTowerVo = CommonApiUtils.towerInfo(userId);
        if(StringUtils.isBlank(currentTowerVo.getMonsterName())){
            IoUtils.println("当前塔层：" + currentTowerVo.getTowerLevel());
            IoUtils.println("当前塔层BOSS未开发");
            return currentTowerVo;
        }

        IoUtils.println("当前塔层：" + currentTowerVo.getTowerLevel());
        IoUtils.println("BOSS：" + currentTowerVo.getMonsterName());
        IoUtils.println("介绍：" + currentTowerVo.getMonsterIntro());
        IoUtils.println("基础攻击：" + currentTowerVo.getMonsterAtk());
        IoUtils.println("基础防御：" + currentTowerVo.getMonsterDef());
        IoUtils.println("基础血量：" + currentTowerVo.getMonsterHp());
        IoUtils.println("通关奖励：" + currentTowerVo.getWeaponName());
        IoUtils.println("装备介绍：" + currentTowerVo.getWeaponIntro());
        IoUtils.println("全服数量：" + currentTowerVo.getServerQty());
        return currentTowerVo;
    }


    public void attack(Integer userId, CurrentTowerVO currentTowerVo) throws IOException {
        TowerResultVO resultVO = CommonApiUtils.towerAttack(userId, currentTowerVo.getMonsterId());

        if (resultVO.getIsSuccess()) {
            IoUtils.println("成功击败BOSS。");
            IoUtils.println(resultVO.getMessage());
        }
    }
}
