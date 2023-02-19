package com.superzyen.page;

import com.superzyen.service.account.Server;
import com.superzyen.util.IoUtils;

import java.io.IOException;

public class WelcomPage {

    public void start() throws IOException {
        IoUtils.println("========================================================================");
        IoUtils.println("============================SUPER ZYEN GAME=============================");
        IoUtils.println("========================================================================");
        IoUtils.println("欢迎来到伪区块链的游戏世界！");
        IoUtils.println("这是一款连接现实和虚拟，超越过去未来的一款跨时空游戏。");
        IoUtils.println("虽然这只是一个文字游戏");
        IoUtils.println("但它不仅仅是个文字游戏...");
        IoUtils.println("========================================================================");
        IoUtils.println("下版本更新内容：");
        IoUtils.println("1 排行榜");
        IoUtils.println("2 新BOSS");
        IoUtils.println("3 强化系统");
        IoUtils.println("========================================================================");
        IoUtils.println("输入回车进入全新的世界吧！！！");
        IoUtils.inputString();
        Server server = new Server();
        boolean isStarted = server.isStarted();
        if(!isStarted){
            IoUtils.println("服务器未启动，请联系作者。");
        }
        new HomePage().start();
    }
}
