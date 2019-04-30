package com.hc.distributed.designMode.factory;

/**
 * game的代理类
 *
 * @author HC
 * @create 2019-04-30 17:44
 */
public class GameProxy {
    private IGame game;

    private boolean isAllow = false;

    public void setAllow(boolean isAllow) {
        this.isAllow = isAllow;
    }

    GameProxy(IGame game, boolean isAllow) {
        this.game = game;
        this.isAllow = isAllow;
    }

    public void play() {
        if (isAllow) {
            System.out.println("your are allowed to play games");
            game.play();
        } else {
            System.out.println("You are not allowed to play games");
        }
    }
}
