package com.hc.distributed.designMode.factory;

/**
 * 简单游戏工厂
 *
 * @author HC
 * @create 2019-04-30 18:31
 */
public class SimpleGameFactory {
    public IGame buyGame(GameEnum type) {
        switch (type) {
            case CS_GAME:return new CSGame();
            case CONTRA_GAME:return new ContraGame();
            default:break;
        }
        return null;
    }
}
