package com.hc.hcbasic.designMode.structuralpattern.adapter;

/**
 * type-C usb接口
 *
 * @author HC
 * @create 2019-05-01 9:50
 */
public class TypeCUsb implements Usb{
    @Override
    public void require() {
        System.out.println("need a Type-C socket");
    }
}
