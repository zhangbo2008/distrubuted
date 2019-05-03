package com.hc.hcbasic.designMode.structuralpattern.flyweight;

import java.util.Hashtable;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 0:32
 */
public class FlyweightFactory {
    private Hashtable<Object, Flyweight> flyweights = new Hashtable<>();

    public FlyweightFactory() {
    }

    public Flyweight getFlyWeight(Object obj) {
        Flyweight flyweight = flyweights.get(obj);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight((String) obj);
            flyweights.put(obj, flyweight);
        }
        return flyweight;
    }


    public int getFlyweightSize() {
        return flyweights.size();
    }
}
