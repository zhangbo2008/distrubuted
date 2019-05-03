package com.hc.hcbasic.designMode.creationalpattern.singleton;

/**
 * 懒汉式优化一
 *
 * @author HC
 * @create 2019-05-01 0:13
 */
public class LazySingletonOpt1 {
    private LazySingletonOpt1() {

    }

    private static final LazySingletonOpt1 singleton = new LazySingletonOpt1();

    public static LazySingletonOpt1 getInstance() {
        return singleton;
    }
}
