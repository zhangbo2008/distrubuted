package com.hc.hcbasic.designMode.creationalpattern.singleton;

/**
 * 懒汉式优化二
 *
 * @author HC
 * @create 2019-05-01 0:16
 */
public class LazySingletonOpt2 {
    private LazySingletonOpt2() {

    }

    private static LazySingletonOpt2 singleton;

    public static LazySingletonOpt2 getInstance() {
        if (null == singleton) {
            synchronized (LazySingletonOpt2.class) {
                if (null == singleton) {
                    singleton = new LazySingletonOpt2();
                }
            }
        }
        return singleton;
    }
}
