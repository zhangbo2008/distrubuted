package com.hc.distributed.designMode.factory;

/**
 * 游戏玩家
 *
 * @author HC
 * @create 2019-04-30 20:15
 */
public class GamePlayer {
    public static void main(String[] args) {
        SimpleGameFactory simpleGameFactory = new SimpleGameFactory();

        IGame csGame = simpleGameFactory.buyGame(GameEnum.CS_GAME);
        GameProxy gameProxy = new GameProxy(csGame, true);
        gameProxy.play();
        gameProxy.setAllow(false);
        gameProxy.play();

        IGame contraGame = simpleGameFactory.buyGame(GameEnum.CONTRA_GAME);
        GameProxy gameProxy1 = new GameProxy(contraGame, true);
        gameProxy1.play();
        gameProxy1.setAllow(false);
        gameProxy1.play();
    }
}
