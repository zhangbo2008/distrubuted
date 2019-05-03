package com.hc.hcbasic.designMode.graphicdesignpattern.strategy;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 0:57
 */
public class StrategyTest {
    public static void main(String[] args) {
        Context context = new Context(new HuaWei());
        context.operate();

        context.setStrategy(new XiaoMi());
        context.operate();
    }
}
