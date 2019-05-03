package thread.wait;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 银行
 *
 * @author HC
 * @create 2019-04-30 14:01
 */
public class Bank {

    private volatile AtomicInteger money = new AtomicInteger(100);

    private Object lock = new Object();

    public Object getLock() {
        return this.lock;
    }

    public void enterMoney(int enterMoney) {
        money.addAndGet(enterMoney);
    }

    public boolean outerMoney(int outerMoney) {
        if (money.get() >= outerMoney) {
            money.getAndUpdate(x -> x - outerMoney);
            return true;
        }
        return false;
    }
}
