package com.hc.hcbasic.designMode.structuralpattern.adapter;

/**
 * a adapter for micro usb
 * there has not use extend but use entrust
 *
 * @author HC
 * @create 2019-05-01 10:01
 */
public class MicroUsbAdater2 implements Usb{

    private MicroUsb microUsb;

    MicroUsbAdater2(MicroUsb microUsb) {
        this.microUsb = microUsb;
    }

    @Override
    public void require() {
        this.microUsb.specificRequest();
    }
}
