package com.hc.distributed.designMode.structuralpattern.proxy;

/**
 * the test class for proxy
 *
 * @author HC
 * @create 2019-05-01 19:46
 */
public class PurChaseProxyTest {
    public static void main(String[] args) {
        Custom custom = new Custom();

        Purchase purchase = new PurChaseProxy(null);
//        Purchase purchase = new PurChaseProxy(custom);
        purchase.buyCar();



    }
}
