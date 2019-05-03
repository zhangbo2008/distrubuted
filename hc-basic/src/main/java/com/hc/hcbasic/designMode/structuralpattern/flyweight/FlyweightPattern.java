package com.hc.hcbasic.designMode.structuralpattern.flyweight;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 0:40
 */
public class FlyweightPattern {
    private FlyweightFactory factory = new FlyweightFactory();
    private Flyweight fly1;
    private Flyweight fly2;
    private Flyweight fly3;
    private Flyweight fly4;
    private Flyweight fly5;
    private Flyweight fly6;

    public FlyweightPattern() {
        fly1 = factory.getFlyWeight("Google");
        fly2 = factory.getFlyWeight("Qutr");
        fly3 = factory.getFlyWeight("Google");
        fly4 = factory.getFlyWeight("Google");
        fly5 = factory.getFlyWeight("Google");
        fly6 = factory.getFlyWeight("Google");
    }

    public void showFlyweight() {
        fly1.operation();
        fly2.operation();
        fly3.operation();
        fly4.operation();
        fly5.operation();
        fly6.operation();
        int objSize = factory.getFlyweightSize();
        System.out.println("objSize = " + objSize);
    }

    public static void main(String[] args) {
        System.out.println("The FlyWeight Pattern!");
        FlyweightPattern fp = new FlyweightPattern();
        fp.showFlyweight();
    }

}
