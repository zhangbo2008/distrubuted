package com.hc.distributed.designMode.creationalpattern.factory;

/**
 * 魂斗罗游戏
 *
 * @author HC
 * @create 2019-04-30 17:48
 */
public class ContraGame implements IGame {
    public ContraGame() {
        System.out.println("buy contra game");
    }
    @Override
    public void play() {
        System.out.println("you are ready to play Contra");
    }
}
