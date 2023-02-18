package com.superzyen.weapon;

import com.superzyen.dto.AccountDto;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;
import com.superzyen.vo.WarehouseVO;

import java.io.IOException;
import java.util.List;

public class Warehouse {

    /**
     * 仓库列表操作
     */
    public void show(AccountDto accountDto) throws IOException {

        List<WarehouseVO> warehouseVOList = CommonApiUtils.warehouse(accountDto.getId());
        if (warehouseVOList.size() <= 0) {
            IoUtils.println("暂无库存");
            return;
        }

        for (WarehouseVO warehouseVO : warehouseVOList) {
            IoUtils.println("=========================================");
            IoUtils.println("装备唯一码：" + warehouseVO.getInstanceId());
            IoUtils.println("装备名：" + warehouseVO.getName());
            IoUtils.println("装备效果说明：" + warehouseVO.getIntro());
            IoUtils.println("状态：" + (warehouseVO.getIsSetup() ? "已装备" : "未装备"));
            IoUtils.println("=========================================");
        }

        boolean escape = false;
        while (true) {
            if (escape) {
                break;
            }

            //咨询仓库操作
            IoUtils.println("请输入你的选择");
            IoUtils.println("1 装备");
            IoUtils.println("2 出售");
            IoUtils.println("3 强化");
            IoUtils.println("其他 返回上一页");
            int select = IoUtils.inputInt();
            switch (select) {
                case 1:
                    this.setup();
                    break;
                case 2:
                    this.sell();
                    break;
                case 3:
                    this.intensify();
                    break;
                default:
                    escape = true;
                    break;
            }
        }

    }


    /**
     * 装备
     */
    public void setup() {
        IoUtils.println("目前只能装备一件，后续装备会覆盖之前的装备。");
        IoUtils.println("请输入你要装备的唯一码:");
        Integer instanceId = IoUtils.inputInt();
        //先检查该用户是否拥有该装备

        //更改装备状态

    }

    /**
     * 出售
     */
    public void sell() {
        //发布到惠交所进行交易

        //交易需要百分之5的手续费，最低为1个游戏币
    }

    /**
     * 强化
     */
    public void intensify() {
        IoUtils.println("该功能未上线。");
    }
}
