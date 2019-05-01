package com.hc.distributed.designMode.structuralpattern.proxy;

/**
 * another people who can buy car for you
 *
 * @author HC
 * @create 2019-05-01 19:42
 */
public class PurChaseProxy implements Purchase {

    private Custom custom;

    public PurChaseProxy(Custom custom) {
        this.custom = custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    @Override
    public void buyCar() {
        if (null == custom) {
            System.out.println("there has nobody want to buy car");
        } else {
            System.out.println("custom has no enough money to buy car");
            System.out.println("custom try to get some money from bank");
            custom.buyCar();
        }
    }
}
