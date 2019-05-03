package com.hc.hcbasic.designMode.structuralpattern.facade;

/**
 * a patient go to hospital for healing
 *
 * @author HC
 * @create 2019-05-01 20:15
 */
public class PatientTest {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.healing();
    }
}
