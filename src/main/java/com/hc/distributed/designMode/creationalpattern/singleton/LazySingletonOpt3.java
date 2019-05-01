package com.hc.distributed.designMode.creationalpattern.singleton;

/**
 * 懒汉式优化三
 *
 * @author HC
 * @create 2019-05-01 0:23
 */
public class LazySingletonOpt3 {
    private LazySingletonOpt3() {

    }

    private static class LazyHolder {
        static final LazySingletonOpt3 singleton = new LazySingletonOpt3();
    }

    public static LazySingletonOpt3 getInstance() {
        return LazyHolder.singleton;
    }
}
