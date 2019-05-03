package com.hc.distributed.designMode.graphicdesignpattern.command;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 2:20
 */
//可以看作是遥控器Invoker
public class Control {
    private Command onCommand, offCommand, changeChannel;

    public Control(Command on, Command off, Command channel) {
        onCommand = on;
        offCommand = off;
        changeChannel = channel;
    }

    public void turnOn() {
        onCommand.execute();
    }

    public void turnOff() {
        offCommand.execute();
    }

    public void changeChannel() {
        changeChannel.execute();
    }
}

