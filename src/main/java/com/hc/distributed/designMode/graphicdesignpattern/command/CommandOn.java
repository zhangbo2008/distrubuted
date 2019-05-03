package com.hc.distributed.designMode.graphicdesignpattern.command;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 2:19
 */
//开机命令ConcreteCommand
public class CommandOn implements Command {
    private Tv myTv;

    public CommandOn(Tv tv) {
        myTv = tv;
    }

    public void execute() {
        myTv.turnOn();
    }
}

