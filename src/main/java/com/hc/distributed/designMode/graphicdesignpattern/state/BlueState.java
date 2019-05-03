package com.hc.distributed.designMode.graphicdesignpattern.state;

import java.awt.*;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 10:59
 */
public class BlueState extends State {

    public void changeState(Context c) {
        System.out.println("变成绿色");
        c.setState(new GreenState());
    }

}
