package com.hc.distributed.designMode.graphicdesignpattern.state;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:04
 */
public class GreenState extends State {
    @Override
    public void changeState(Context c) {
        System.out.println("不再变色");
    }
}
