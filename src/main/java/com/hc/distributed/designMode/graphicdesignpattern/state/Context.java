package com.hc.distributed.designMode.graphicdesignpattern.state;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:00
 */
public class Context {

    private State state = null;

    //setState是用来改变state的状态 使用setState实现状态的切换
    public void setState(State state) {
        this.state = state;
    }

    public void push() {
        state.changeState(this);
    }
}

