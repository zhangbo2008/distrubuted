package com.hc.hcbasic.designMode.graphicdesignpattern.command;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 2:21
 */
//测试类Client
public class Client {
    public static void main(String[] args) {
        // 命令接收者Receiver
        Tv myTv = new Tv();
        // 开机命令ConcreteCommond
        CommandOn on = new CommandOn(myTv);
        // 关机命令ConcreteCommond
        CommandOff off = new CommandOff(myTv);
        // 频道切换命令ConcreteCommond
        CommandChange channel = new CommandChange(myTv, 2);
        // 命令控制对象Invoker
        Control control = new Control(on, off, channel);

// 开机
        control.turnOn();
        // 切换频道
        control.changeChannel();
        // 关机
        control.turnOff();
    }
}

