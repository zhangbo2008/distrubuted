package com.hc.distributed.designMode.bridge;

/**
 *
 *
 * @author HC
 * @create 2019-05-01 20:39
 */
public abstract class Shape {
    Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void point();
}
