package thread.wait;

/**
 * 取款
 *
 * @author HC
 * @create 2019-04-30 14:20
 */
public class OuterMoney implements Runnable {
    private Bank bank;

    private int outerMoney;

    private String name;

    private int waitTimes = 0;

    OuterMoney(Bank bank, int outerMoney, String name) {
        this.bank = bank;
        this.outerMoney = outerMoney;
        this.name = name;
    }

    @Override
    public void run() {
        int MAX_WAIT_TIMES = 10;

        synchronized (bank.getLock()) {
            System.out.println(name + "尝试取款：" + outerMoney);
            while (!bank.outerMoney(outerMoney)) {
                if (++waitTimes >= MAX_WAIT_TIMES) {
                    System.out.println(name + "等太久，放弃取款");
                    return;
                }
                System.out.println(name + "取款失败,正在等待他人存款");
                try {
                    bank.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + "取款成功：" + outerMoney);
        }
    }
}
