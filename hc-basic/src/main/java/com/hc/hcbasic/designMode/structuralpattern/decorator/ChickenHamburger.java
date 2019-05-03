package com.hc.hcbasic.designMode.structuralpattern.decorator;

/**
 * chicken hamburger
 *
 * @author HC
 * @create 2019-05-01 16:47
 */
public class ChickenHamburger extends Hamburger {

    public ChickenHamburger() {
        name = "a hamburger with chicken";
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
