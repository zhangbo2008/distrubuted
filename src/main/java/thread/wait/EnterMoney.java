package thread.wait;

/**
 * 存钱
 *
 * @author HC
 * @create 2019-04-30 14:07
 */
public class EnterMoney implements Runnable{

    private Bank bank;

    private int enterMoney;

    private String name;

    private int waitTimes = 0;

    EnterMoney(Bank bank, int enterMoney, String name) {
        this.bank = bank;
        this.enterMoney = enterMoney;
        this.name = name;
    }

    @Override
    public void run() {

        synchronized (bank.getLock()) {

            try {
                Thread.sleep(1000L);    //用线程sleep表示存款的过程
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bank.enterMoney(enterMoney);
            bank.getLock().notifyAll();
            System.out.println(name + "存款成功：" + enterMoney);
        }
    }
}
