package com.hc.distributed.designMode.graphicdesignpattern.visitor;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:27
 */
public class ConcreteElement extends Element {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
