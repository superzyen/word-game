package com.superzyen.service.weapon;

import com.superzyen.dto.AccountDto;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;
import com.superzyen.vo.ResultVO;
import com.superzyen.vo.WarehouseVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        }

        Map<Integer, WarehouseVO> idWarehouseMap = warehouseVOList.stream()
                .collect(Collectors.toMap(WarehouseVO::getInstanceId, Function.identity(), (k1, k2) -> k1));

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
                    this.setup(idWarehouseMap, accountDto.getId());
                    break;
                case 2:
                    this.sell(idWarehouseMap, accountDto.getId());
                    break;
                case 3:
                    this.intensify(idWarehouseMap);
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
    public void setup(Map<Integer, WarehouseVO> idWarehouseMap, Integer userId) throws IOException {
        IoUtils.println("目前只能装备一件，后续装备会覆盖之前的装备。");
        IoUtils.println("请输入你要装备的唯一码:");
        Integer instanceId = IoUtils.inputInt();
        if (idWarehouseMap.containsKey(instanceId)) {
            boolean res = CommonApiUtils.setupInstance(userId, instanceId);

            if (res) {
                IoUtils.println("装备成功。");
            } else {
                IoUtils.println("装备失败。");
            }

        } else {
            IoUtils.println("唯一码错误，你未拥有该装备！！");
        }
    }

    /**
     * 出售
     */
    public void sell(Map<Integer, WarehouseVO> idWarehouseMap, Integer userId) throws IOException {
        //发布到惠交所进行交易
        //交易需要百分之5的手续费，最低为1个游戏币
        IoUtils.println("交易需要总价百分之5的手续费（四舍五入），最低为1个币");
        IoUtils.println("请输入你要出售的装备唯一码:");
        Integer instanceId = IoUtils.inputInt();
        if (idWarehouseMap.containsKey(instanceId)) {

            IoUtils.println("请输入你要出售的价格:");
            long price = IoUtils.inputLong();

            ResultVO resultVO = CommonApiUtils.sellInstance(userId, instanceId, price);
            if (resultVO.getIsSuccess()) {
                IoUtils.println("出售成功。");
            } else {
                IoUtils.println("出售失败。");
            }
            IoUtils.println(resultVO.getMessage());

        } else {
            IoUtils.println("唯一码错误，你未拥有该装备！！");
        }
    }

    /**
     * 强化
     */
    public void intensify(Map<Integer, WarehouseVO> idWarehouseMap) {
        IoUtils.println("该功能未上线。");
    }
}
