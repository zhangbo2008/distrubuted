package com.hc.hcbasic.designMode.creationalpattern.factory;

/**
 * Abstract Factory for cs game
 *
 * @author HC
 * @create 2019-04-30 21:44
 */
public class AbstractCsGameFactory implements AbstractGameFactory {


    @Override
    public IGame buyGame() {
        return new CSGame();
    }

    @Override
    public IGameAccount buyGameAccount() {
        return new CSGameAccount();
    }
}
