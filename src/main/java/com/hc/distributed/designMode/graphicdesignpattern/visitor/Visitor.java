package com.hc.distributed.designMode.graphicdesignpattern.visitor;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 11:28
 */
public class Visitor implements IVisitor {
    @Override
    public void visit(Element element) {
        element.accept(this);
    }
}
