package com.hc.distributed.designMode.graphicdesignpattern.memento;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 10:48
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
