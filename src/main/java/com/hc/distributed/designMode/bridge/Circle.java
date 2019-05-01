package com.hc.distributed.designMode.bridge;

/**
 * Circle
 *
 * @author HC
 * @create 2019-05-01 20:42
 */
public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void point() {
        System.out.println(super.color.draw() + "circle");
    }
}
