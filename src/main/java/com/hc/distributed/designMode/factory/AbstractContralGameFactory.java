package com.hc.distributed.designMode.factory;

/**
 * abstract factory for contral
 *
 * @author HC
 * @create 2019-04-30 21:50
 */
public class AbstractContralGameFactory implements AbstractGameFactory {
    @Override
    public IGame buyGame() {
        return new ContraGame();
    }

    @Override
    public IGameAccount buyGameAccount() {
        return new ContralGameAccount();
    }
}
