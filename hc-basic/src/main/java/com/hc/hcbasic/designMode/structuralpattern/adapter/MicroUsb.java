package com.hc.hcbasic.designMode.structuralpattern.adapter;

/**
 * 安卓插口,一个老类，没有实现Usb接口，且实现方法与后者不同
 *
 * @author HC
 * @create 2019-05-01 9:48
 */
public class MicroUsb {
    public void specificRequest() {
        System.out.println("need a microUsb socket");
    }
}
