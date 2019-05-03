package com.hc.distributed.designMode.graphicdesignpattern.visitor;

import org.aspectj.weaver.Dump;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:24
 */
public abstract class Element {
    public abstract void accept(IVisitor visitor);
}
