package com.hc.distributed.designMode.structuralpattern.composite;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-01 21:12
 */
public abstract class Component {
    String name;

    public abstract void add(Component c);

    public abstract void remove(Component c);

    public abstract void eachChild();
}