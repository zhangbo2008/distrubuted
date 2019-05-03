package com.hc.hcbasic.designMode.structuralpattern.bridge;

/**
 * the test class for bridge
 *
 * @author HC
 * @create 2019-05-01 20:43
 */
public class ColorShapeTest {
    public static void main(String[] args) {
        Color blank = new Blank();
        Color red = new Red();

        Shape circle = new Circle(blank);
        circle.point();
        circle.setColor(red);
        circle.point();

        Shape rectangele = new Rectangle(blank);
        rectangele.point();
        rectangele.setColor(red);
        rectangele.point();
    }
}
