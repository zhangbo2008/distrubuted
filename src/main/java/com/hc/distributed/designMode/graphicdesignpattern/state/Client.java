package com.hc.distributed.designMode.graphicdesignpattern.state;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:06
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new BlueState());
        context.push();
        context.push();

        context.setState(new GreenState());
        context.push();
    }
}
