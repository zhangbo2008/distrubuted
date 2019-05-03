package com.hc.hcbasic.designMode.creationalpattern.prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Prototype
 *
 * @author HC
 * @create 2019-05-01 1:42
 */
public class Prototype implements Cloneable, Serializable {
    public List<String> list = new ArrayList<>();

    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
            prototype.list = CloneFactory.clone(list);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }

    public void show() {
        System.out.println("原型模式：一个接口一个方法");
    }
}
