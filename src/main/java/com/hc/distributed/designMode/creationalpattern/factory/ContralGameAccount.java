package com.hc.distributed.designMode.creationalpattern.factory;

/**
 * the game account for contra
 *
 * @author HC
 * @create 2019-04-30 21:42
 */
public class ContralGameAccount implements IGameAccount {

    ContralGameAccount() {
        System.out.println("buy a contra game account");
    }

    @Override
    public void login() {
        System.out.println("login contra by account");
    }
}
