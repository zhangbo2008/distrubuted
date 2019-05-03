package com.hc.hcbasic.designMode.structuralpattern.adapter;

/**
 * the test class for usb adapter
 *
 * @author HC
 * @create 2019-05-01 9:52
 */
public class UsbAdapterTest {
    public static void main(String[] args) {
        Usb typeCUsb = new TypeCUsb();
        typeCUsb.require();

//        Usb microUsbAdater = new MicroUsbAdater();
//        microUsbAdater.require();

        Usb microUsbAdater2 = new MicroUsbAdater2(new MicroUsb());
        microUsbAdater2.require();
    }
}
