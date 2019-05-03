package com.hc.distributed.designMode.graphicdesignpattern.memento;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 10:48
 */
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("状态1");
        System.out.println("初始状态:" + originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("状态2");
        System.out.println("改变后状态:" + originator.getState());
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("恢复后状态:" + originator.getState());
    }
}