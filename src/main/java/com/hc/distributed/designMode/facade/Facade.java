package com.hc.distributed.designMode.facade;

/**
 * a facade in hospital
 *
 * @author HC
 * @create 2019-05-01 20:05
 */
public class Facade {

    private DoctorSubSyetem doctorSubSyetem = new DoctorSubSyetem();

    private MedicineSubSystem medicineSubSystem = new MedicineSubSystem();

    private SettleAccountsSubSystem settleAccountsSubSystem = new SettleAccountsSubSystem();

    public void healing() {
        doctorSubSyetem.seeDoctor();
        settleAccountsSubSystem.settleAccounts();
        medicineSubSystem.getMedicine();
    }
}
