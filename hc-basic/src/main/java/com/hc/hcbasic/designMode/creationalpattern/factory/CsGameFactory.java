package com.hc.hcbasic.designMode.creationalpattern.factory;

/**
 * the factory of CsGame
 *
 * @author HC
 * @create 2019-04-30 20:20
 */
public class CsGameFactory implements GameFactory {
    @Override
    public IGame buyGame() {
        return new CSGame();
    }
}
