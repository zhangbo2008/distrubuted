package com.hc.hcbasic.designMode.graphicdesignpattern.visitor;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:24
 */
public abstract class Element {
    public abstract void accept(IVisitor visitor);
}
