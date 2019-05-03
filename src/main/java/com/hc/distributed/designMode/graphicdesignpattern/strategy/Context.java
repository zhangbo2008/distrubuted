package com.hc.distributed.designMode.graphicdesignpattern.strategy;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 0:55
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        strategy.operate();
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }
}
