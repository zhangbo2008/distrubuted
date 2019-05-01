package com.hc.distributed.designMode.bridge;

/**
 * Rectangle
 *
 * @author HC
 * @create 2019-05-01 20:42
 */
public class Rectangle extends Shape {
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void point() {
        System.out.println(super.color.draw() + "rectangle");
    }
}
