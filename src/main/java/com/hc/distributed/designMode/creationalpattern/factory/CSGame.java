package com.hc.distributed.designMode.creationalpattern.factory;

/**
 * game实现类
 *
 * @author HC
 * @create 2019-04-30 17:43
 */
public class CSGame implements IGame{

    CSGame() {
        System.out.println("buy CS game");
    }
    @Override
    public void play() {
        System.out.println("you are ready to play CS");
    }
}
