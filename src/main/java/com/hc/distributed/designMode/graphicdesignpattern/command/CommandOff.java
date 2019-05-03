package com.hc.distributed.designMode.graphicdesignpattern.command;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 2:19
 */
public class CommandOff implements Command {
    private Tv myTv;

    public CommandOff(Tv tv) {
        myTv = tv;
    }

    public void execute() {
        myTv.turnOff();
    }
}

