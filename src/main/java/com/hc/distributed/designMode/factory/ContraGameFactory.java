package com.hc.distributed.designMode.factory;

/**
 * Factory Method
 * create contraGame
 *
 * @author HC
 * @create 2019-04-30 20:29
 */
public class ContraGameFactory implements GameFactory {
    @Override
    public IGame buyGame() {
        return new ContraGame();
    }
}
