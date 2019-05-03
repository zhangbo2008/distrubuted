package com.hc.hcbasic.designMode.graphicdesignpattern.memento;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 10:46
 */
public
class Originator {
    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(this.state);
    }

    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}
