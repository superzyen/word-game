package com.superzyen.mining;

import com.superzyen.common.ServerSetting;
import com.superzyen.dto.AccountDto;
import com.superzyen.util.CommonApiUtils;
import com.superzyen.util.HttpUtils;
import com.superzyen.util.IoUtils;

import java.io.IOException;

/**
 * 矿场
 */
public class MineField {

    public void mining(int loop) throws IOException {
        //先判断是否还有矿
        if (!remain()) {
            IoUtils.println("矿场已经被挖完了，无法再挖。");
            return;
        }

        if(loop==-1){
            //无限次挖矿
            while (true) {
                boolean isSucceed = start(new SimpleMiningStrategy());
                if (isSucceed) {
                    IoUtils.println("恭喜！！挖矿成功");
                    //奖励金币
                    award();
                    break;
                } else {
                    IoUtils.println("还未成功...请耐心等待");
                }
            }
            return;
        }


        for (int i = 0; i < loop; i++) {
            boolean isSucceed = start(new SimpleMiningStrategy());
            if (isSucceed) {
                IoUtils.println("恭喜！！挖矿成功");
                //奖励金币
                award();
                break;
            } else {
                IoUtils.println("还未成功...请耐心等待");
            }
        }
    }

    /**
     * 挖矿
     */
    private boolean start(IDefaultMiningStrategy strategy) {
        return strategy.calculate();
    }

    /**
     * 是否有矿
     */
    private boolean remain() throws IOException {
        String res = HttpUtils.get(ServerSetting.getPath() + "/server/remain");
        return Boolean.valueOf(res);
    }

    /**
     * 奖励
     */
    private void award() throws IOException {

        AccountDto accountDto = CommonApiUtils.userInfo();
        if (null != accountDto) {
            CommonApiUtils.award(accountDto.getId());
        }

    }
}
