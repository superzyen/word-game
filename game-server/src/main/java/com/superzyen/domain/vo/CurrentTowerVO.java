package com.superzyen.domain.vo;

import lombok.Data;

@Data
public class CurrentTowerVO {

    private Integer towerId;
    private Integer towerLevel;

    private Integer monsterId;
    private String monsterName;
    private String monsterIntro;
    private Double monsterAtk;
    private Double monsterDef;
    private Double monsterHp;
    private Integer monsterSpeed;
    /**
     * 首通id
     */
    private Integer firstPassId;
    /**
     * 首通名字
     */
    private String firstPassName;

    private Integer weaponId;
    private String weaponName;
    private String weaponIntro;
    private Integer serverQty;

}
