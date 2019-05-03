package com.hc.hcbasic.designMode.structuralpattern.proxy;

/**
 * describe a people who want to buy a car
 *
 * @author HC
 * @create 2019-05-01 19:41
 */
public class Custom implements Purchase {

    @Override
    public void buyCar() {
        System.out.println("I get a car");
    }
}
