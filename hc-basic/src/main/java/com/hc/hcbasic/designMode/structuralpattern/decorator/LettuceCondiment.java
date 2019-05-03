package com.hc.hcbasic.designMode.structuralpattern.decorator;

/**
 * decorator a lettuce for hamburger
 *
 *
 * @author HC
 * @create 2019-05-01 16:51
 */
public class LettuceCondiment extends Condiment {

    Hamburger hamburger;

    public LettuceCondiment(Hamburger hamburger){
        this.hamburger = hamburger;
    }

    @Override
    public String getName() {
        return hamburger.getName() + "add lettuce ";
    }

    @Override
    public double getPrice() {
        return hamburger.getPrice() + 20;
    }
}
