package com.hc.distributed.designMode.factory;

/**
 * the gameAccount of cs
 *
 * @author HC
 * @create 2019-04-30 21:40
 */
public class CSGameAccount implements IGameAccount {

    CSGameAccount() {
        System.out.println("buy a cs account");
    }

    @Override
    public void login() {
        System.out.println("login the cs game by account");
    }
}
