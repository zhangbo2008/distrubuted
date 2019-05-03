package com.hc.hcbasic.designMode.creationalpattern.singleton;

/**
 * 懒汉式单例
 *
 * @author HC
 * @create 2019-04-30 23:57
 */
public class LazySingleton {
    private LazySingleton() {

    }

    private static LazySingleton lazySingleton;

    public static LazySingleton getInstance() {
        if (null == lazySingleton) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
