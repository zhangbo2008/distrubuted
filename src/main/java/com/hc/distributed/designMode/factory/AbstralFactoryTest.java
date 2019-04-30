package com.hc.distributed.designMode.factory;

/**
 * test the abstral factory
 *
 * @author HC
 * @create 2019-04-30 21:52
 */
public class AbstralFactoryTest {
    public static void main(String[] args) {
        AbstractGameFactory abstractContralGameFactory = new AbstractContralGameFactory();
        abstractContralGameFactory.buyGame();
        abstractContralGameFactory.buyGameAccount();

        AbstractGameFactory abstractCsGameFactory = new AbstractCsGameFactory();
        abstractCsGameFactory.buyGame();
        abstractCsGameFactory.buyGameAccount();
    }
}
