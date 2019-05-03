package com.hc.hcbasic.designMode.structuralpattern.decorator;

/**
 * hanburge
 * the class was ready to be decorated
 *
 * @author HC
 * @create 2019-05-01 15:07
 */
public abstract class Hamburger {
    protected  String name ;

    public String getName(){
        return name;
    }

    public abstract double getPrice();
}
