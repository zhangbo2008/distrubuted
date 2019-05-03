package com.hc.hcbasic.designMode.structuralpattern.adapter;

/**
 * a adpter for micro usb and Type-C usb
 *
 * @author HC
 * @create 2019-05-01 9:51
 */
public class MicroUsbAdater extends MicroUsb implements Usb {
    @Override
    public void require() {
        super.specificRequest();
    }
}
