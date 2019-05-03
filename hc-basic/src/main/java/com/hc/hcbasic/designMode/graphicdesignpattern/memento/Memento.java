package com.hc.hcbasic.designMode.graphicdesignpattern.memento;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 10:48
 */
public
class Memento {
    private String state = "";

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
