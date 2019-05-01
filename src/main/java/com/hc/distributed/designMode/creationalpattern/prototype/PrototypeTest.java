package com.hc.distributed.designMode.creationalpattern.prototype;

/**
 * 原型模式测试类
 *
 * @author HC
 * @create 2019-05-01 1:46
 */
public class PrototypeTest {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        for (int i = 0; i < 10; i++) {
//            Prototype clonePrototype = prototype.clone();
            Prototype clonePrototype = CloneFactory.clone(prototype);
            clonePrototype.show();
            System.out.println(clonePrototype.list == prototype.list);


        }
    }
}
