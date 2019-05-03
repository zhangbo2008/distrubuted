package com.hc.hcbasic.designMode.structuralpattern.decorator;

/**
 * decorator a Chilli Condiment for Hamburger
 *
 * @author HC
 * @create 2019-05-01 16:54
 */
public class ChilliCondiment extends Condiment {

    Hamburger hamburger;

    public ChilliCondiment(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + "add chilli ";
    }

    @Override
    public double getPrice() {
        return hamburger.getPrice() + 5;
    }
}
