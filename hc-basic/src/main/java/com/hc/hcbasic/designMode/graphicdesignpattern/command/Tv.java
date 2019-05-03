package com.hc.hcbasic.designMode.graphicdesignpattern.command;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 2:18
 */
//命令接收者Receiver
public class Tv {
    public int currentChannel = 0;

    public void turnOn() {
        System.out.println("The televisino is on.");
    }

    public void turnOff() {
        System.out.println("The television is off.");
    }

    public void changeChannel(int channel) {
        this.currentChannel = channel;
        System.out.println("Now TV channel is " + channel);
    }
}

