package com.hc.hcbasic.designMode.creationalpattern.singleton;

/**
 * 单例模式测试类
 *
 * @author HC
 * @create 2019-05-01 0:42
 */
public class SingletonTest {

    // com.hc.distributed.designMode.creationalpattern.singleton.LazySingletonThread@70d192f2
    // com.hc.distributed.designMode.creationalpattern.singleton.LazySingletonThread@7aa3aa20
    // 存在不同步现象
    static class LazySingletonThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LazySingleton.getInstance());
        }
    }

    // 不存在不同步问题
    static class LazySingletonOpt1Thread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LazySingletonOpt1.getInstance());
        }
    }

    static class LazySingletonOpt2Thread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LazySingletonOpt2.getInstance());
        }
    }

    public static void main(String[] args) {
        System.out.println("-------测试LazySingleton-------");
        for (int i = 0; i < 10; i++) {
            new Thread(new LazySingletonThread()).start();
        }

        System.out.println("-------测试LazySingletonOpt1-------");
//        for (int i = 0; i < 10; i++) {
//            new Thread(new LazySingletonOpt1Thread()).start();
//        }

        System.out.println("-------测试LazySingletonOpt2-------");
//        for (int i = 0; i < 10; i++) {
//            new Thread(new LazySingletonOpt2Thread()).start();
//        }
    }

}
