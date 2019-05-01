package com.hc.distributed.designMode.prototype;

/**
 * 原型模式测试类
 *
 * @author HC
 * @create 2019-05-01 1:46
 */
public class PrototypeTest {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.list.add("ssss");
        for (int i = 0; i < 10; i++) {
            Prototype clonePrototype = prototype.clone();
            System.out.println(clonePrototype);
            clonePrototype.show();
            System.out.println(clonePrototype.list);
        }
    }
}
