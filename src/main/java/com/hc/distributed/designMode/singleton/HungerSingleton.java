package com.hc.distributed.designMode.singleton;

/**
 * 饿汉式
 *
 * @author HC
 * @create 2019-05-01 0:18
 */
public class HungerSingleton {
    private HungerSingleton() {

    }

    private static HungerSingleton singleton;

    public static synchronized HungerSingleton getInstance() {
        if (null == singleton) {
            singleton = new HungerSingleton();
        }
        return singleton;
    }
}
