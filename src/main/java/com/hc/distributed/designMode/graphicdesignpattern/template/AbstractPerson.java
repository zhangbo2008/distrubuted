package com.hc.distributed.designMode.graphicdesignpattern.template;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 1:04
 */
public abstract class AbstractPerson{
    public void prepareGotoSchool(){
        dressUp();
        eatBreakfast();
        takeThings();
    }
    protected abstract void dressUp();
    protected abstract void eatBreakfast();
    protected abstract void takeThings();
}
