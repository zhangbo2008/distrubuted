package com.hc.distributed.designMode.creationalpattern.factory;

/**
 * Abstract Factory for Game
 *
 * @author HC
 * @create 2019-04-30 21:38
 */
public interface AbstractGameFactory {

    IGame buyGame();

    IGameAccount buyGameAccount();
}
