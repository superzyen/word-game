package com.superzyen.service.tower;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superzyen.domain.entity.*;
import com.superzyen.domain.request.AttackRequest;
import com.superzyen.domain.vo.CurrentTowerVO;
import com.superzyen.domain.vo.TowerResultVO;
import com.superzyen.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TowerService {

    public static final List<Integer> PASS_MONSTER_ID_LIST = new ArrayList<>(Arrays.asList(1));

    @Autowired
    private TowerMapper towerMapper;

    @Autowired
    private TowerMonsterMapper towerMonsterMapper;
    @Autowired
    private WeaponMapper weaponMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private WeaponInstanceMapper weaponInstanceMapper;

    public String show(Integer userId) {
        CurrentTowerVO currentTowerVo = new CurrentTowerVO();

        //先查看用户所在塔层
        List<Tower> towerList = towerMapper.selectList(new QueryWrapper<Tower>().eq("user_id", userId));
        int towerLevel = 1;
        if (towerList.size() > 0) {
            towerLevel = towerList.size() + 1;
        }

        int towerId = 0;
        if (towerList.size() > 0) {
            towerId = towerList.get(0).getId();
        }

        currentTowerVo.setTowerId(towerId);
        currentTowerVo.setTowerLevel(towerLevel);

        //找到对应塔层BOSS
        List<TowerMonster> towerMonsters = towerMonsterMapper.selectList(new QueryWrapper<TowerMonster>().eq("tower_level", towerLevel));
        if (towerMonsters.size() <= 0) {
            return JSON.toJSONString(currentTowerVo);
        }
        TowerMonster towerMonster = towerMonsters.get(0);

        currentTowerVo.setMonsterId(towerMonster.getId());
        currentTowerVo.setMonsterName(towerMonster.getName());
        currentTowerVo.setMonsterIntro(towerMonster.getIntro());
        currentTowerVo.setMonsterAtk(towerMonster.getAtk());
        currentTowerVo.setMonsterDef(towerMonster.getDef());
        currentTowerVo.setMonsterHp(towerMonster.getHp());
        currentTowerVo.setMonsterSpeed(towerMonster.getSpeed());
        currentTowerVo.setFirstPassId(towerMonster.getFirstPassUser());
        currentTowerVo.setFirstPassName(towerMonster.getFirstPassName());

        //获取奖励武器详情
        Integer weaponId = towerMonsters.get(0).getWeaponId();
        if (weaponId == 0) {
            return JSON.toJSONString(currentTowerVo);
        }
        Weapon weapon = weaponMapper.selectById(weaponId);
        if (null == weapon) {
            return JSON.toJSONString(currentTowerVo);
        }

        currentTowerVo.setWeaponId(weaponId);
        currentTowerVo.setWeaponName(weapon.getName());
        currentTowerVo.setWeaponIntro(weapon.getIntro());
        currentTowerVo.setServerQty(weapon.getServerQty());

        return JSON.toJSONString(currentTowerVo);

    }

    /**
     * 攻击BOSS
     */
    @Transactional(rollbackFor = Exception.class)
    public String attack(AttackRequest request) {
        Integer monsterId = request.getMonsterId();
        TowerMonster towerMonster = towerMonsterMapper.selectById(monsterId);

        TowerResultVO resultVO = null;
        if (PASS_MONSTER_ID_LIST.contains(towerMonster.getId())) {
            resultVO = this.passBoss(request, towerMonster);
        } else {
            resultVO = attackBoss();
        }

        return JSON.toJSONString(resultVO);
    }

    /**
     * 秒通BOSS
     */
    @Transactional(rollbackFor = Exception.class)
    public TowerResultVO passBoss(AttackRequest request, TowerMonster towerMonster) {

        TowerResultVO resultVO = new TowerResultVO();

        //首通记录
        TowerMonster dbMonster = towerMonsterMapper.selectById(towerMonster.getId());
        Account account = accountMapper.selectById(request.getUserId());

        if (StringUtils.isBlank(dbMonster.getFirstPassName())) {
            dbMonster.setFirstPassUser(request.getUserId());
            dbMonster.setFirstPassName(account.getName());
            towerMonsterMapper.updateById(dbMonster);
        }

        //爬塔记录
        Tower tower = new Tower()
                .setTowerLevel(towerMonster.getTowerLevel())
                .setMonsterId(towerMonster.getId())
                .setUserId(account.getId())
                .setCreateTime(request.getTime());

        towerMapper.insert(tower);

        //装备奖励
        if (0 != dbMonster.getWeaponId()) {
            Weapon weapon = weaponMapper.selectById(dbMonster.getWeaponId());
            Integer serverQty = weapon.getServerQty();

            //获取已经发放的装备数量
            List<WeaponInstance> instances = weaponInstanceMapper.selectList(new QueryWrapper<WeaponInstance>().eq("weapon_id", weapon.getId()));
            if (instances.size() < serverQty) {

                WeaponInstance weaponInstance = new WeaponInstance()
                        .setWeaponId(weapon.getId())
                        .setUserId(account.getId())
                        .setIsSetup(false);

                weaponInstanceMapper.insert(weaponInstance);

                resultVO.setIsAward(true);
                resultVO.setMessage("恭喜获得奖励：" + weapon.getName());
            }else{
                resultVO.setIsAward(false);
                resultVO.setMessage("奖励无剩余");
            }
        }

        resultVO.setIsSuccess(true);
        return resultVO;
    }


    /**
     * 常规计算BOSS
     */
    @Transactional(rollbackFor = Exception.class)
    public TowerResultVO attackBoss() {
       TowerResultVO towerResultVO =  new TowerResultVO();
        towerResultVO.setIsSuccess(false);
        towerResultVO.setMessage("被BOSS击败！！");
        towerResultVO.setIsAward(false);
        return towerResultVO;
    }
}
