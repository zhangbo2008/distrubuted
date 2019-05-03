package com.hc.hcbasic.designMode.structuralpattern.flyweight;

/**
 * @author HC
 * @create 2019-05-02 0:27
 */
public class ConcreteFlyweight extends Flyweight {
    private String string;

    public ConcreteFlyweight(String str) {
        string = str;
    }

    public void operation() {
        System.out.println("Concrete---Flyweight : " + string);
    }
}
