package com.hc.hcbasic.designMode.structuralpattern.composite;

/**
 * left node of component
 *
 * @author HC
 * @create 2019-05-01 21:13
 */
public class Leaf extends Component {

    @Override
    public void add(Component c) {
        System.out.println("");
    }

    @Override
    public void remove(Component c) {
        System.out.println("");
    }

    @Override
    public void eachChild() {
        System.out.println(name + "执行了");
    }

}