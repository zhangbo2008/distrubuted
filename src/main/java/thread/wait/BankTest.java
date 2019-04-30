package thread.wait;

/**
 * 银行测试类
 *
 * @author HC
 * @create 2019-04-30 14:24
 */
public class BankTest {

    public static void main(String[] args) {
        Bank bank = new Bank();

        new Thread(new OuterMoney(bank, 1000, "Json")).start();
        new Thread(new OuterMoney(bank, 100, "Mark")).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new EnterMoney(bank, 1000, "Hash")).start();
    }
}
