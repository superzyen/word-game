package com.superzyen.service.exchange;

import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.IoUtils;
import com.superzyen.vo.ExchangeListVO;
import com.superzyen.vo.ResultVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exchange {

    /**
     * 商品列表
     */
    public List<ExchangeListVO> list() throws IOException {
        List<ExchangeListVO> exchangeListVOList = CommonApiUtils.exchangeList();
        if (exchangeListVOList == null) {
            IoUtils.println("交易所暂无商品");
            return null;
        }

        for (ExchangeListVO exchangeListVO : exchangeListVOList) {
            IoUtils.println("=============================================");
            IoUtils.println("商品id：" + exchangeListVO.getId());
            IoUtils.println("装备唯一码：" + exchangeListVO.getInstanceId());
            IoUtils.println("装备名称：" + exchangeListVO.getName());
            IoUtils.println("装备使用说明：" + exchangeListVO.getIntro());
            IoUtils.println("全服数量：" + exchangeListVO.getServerQty());
            IoUtils.println("使用类型：" + getUsageTypeString(exchangeListVO.getUsageType()));
            if (null != exchangeListVO.getUsageNum()) {
                IoUtils.println("使用次数：" + String.valueOf(exchangeListVO.getUsageNum()));
            }
            if (null != exchangeListVO.getFrozenDay()) {
                IoUtils.println("冷却周期：" + String.valueOf(exchangeListVO.getFrozenDay()));
            }
        }

        return exchangeListVOList;
    }

    /**
     * 交易操作
     */
    public void operate(List<ExchangeListVO> exchangeListVOList, Integer userId) throws IOException {
        if (exchangeListVOList == null) {
            return;
        }

        Map<Integer, ExchangeListVO> idExchangeVOMap = exchangeListVOList.stream()
                .collect(Collectors.toMap(ExchangeListVO::getId, Function.identity(), (k1, k2) -> k1));

        boolean isEscape = false;
        while (true) {
            if (isEscape) {
                break;
            }
            IoUtils.println("1 返回上一页");
            IoUtils.println("2 购买");
            IoUtils.println("3 商品列表");
            IoUtils.println("请输入你的选择");
            int select = IoUtils.inputInt();
            switch (select) {
                case 1:
                    isEscape = true;
                    break;
                case 2:
                    IoUtils.println("请输入你想要购买的商品id：");
                    int goodsId = IoUtils.inputInt();
                    if (idExchangeVOMap.containsKey(goodsId)) {
                        buy(idExchangeVOMap.get(goodsId), userId);
                    } else {
                        IoUtils.println("不存在该商品，请重新选择！！");
                        break;
                    }
                    break;
                case 3:
                    this.list();
                    break;
                default:
                    break;
            }
        }
    }

    private String getUsageTypeString(int usageType) {
        switch (usageType) {
            case ExchangeListVO.USAGETYPE_1:
                return "无限次";
            case ExchangeListVO.USAGETYPE_2:
                return "单次";
            case ExchangeListVO.USAGETYPE_3:
                return "指定次数";
            case ExchangeListVO.USAGETYPE_4:
                return "冷却时间";
            default:
                return "null";
        }
    }

    private void buy(ExchangeListVO exchangeListVO, Integer userId) throws IOException {
        ResultVO resultVO = CommonApiUtils.buyInstance(exchangeListVO.getId(), userId);
        if (resultVO.getIsSuccess()) {
            IoUtils.println("购买成功。");
        } else {
            IoUtils.println("购买失败！！");
        }
        IoUtils.println(resultVO.getMessage());
    }
}
