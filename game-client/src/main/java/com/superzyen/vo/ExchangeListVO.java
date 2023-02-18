package com.superzyen.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExchangeListVO {
    public static final int USAGETYPE_1 = 1;
    public static final int USAGETYPE_2 = 2;
    public static final int USAGETYPE_3 = 3;
    public static final int USAGETYPE_4 = 4;

    private Integer id;
    private Integer instanceId;
    private Integer weaponId;
    private Integer ownerId;
    private String name;
    private String intro;
    /**
     * 全服数量
     */
    private Integer serverQty;
    /**
     * 使用类型(1. 无限次， 2. 单次， 3. 指定次数， 4. 冷却时间)
     */
    private Integer usageType;
    /**
     * 配合使用类型,指定次数
     */
    private Integer usageNum;
    /**
     * 配合使用类型,冷却时间，单位 天
     */
    private Integer frozenDay;
    /**
     * 装备类型（DEFAULT:默认类型）
     */
    private String type;
}
