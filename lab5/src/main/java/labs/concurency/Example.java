package labs.concurency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

/**
 * Created by pawel on 27.11.15.
 */
public class Example {

    ExecutorService service = Executors.newFixedThreadPool(10);

    AtomicInteger some = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        new Example().start();
    }

    private void start() throws InterruptedException {
        while (true) {
            Account account1 = new Account("John", 1);
            Account account2 = new Account("Michael", 1);

            Thread t1 = new Thread(getRunnable(account1, account2));
            Thread t2 = new Thread(getRunnable(account1, account2));
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            if (account1.getAmount() != 0 || account2.getAmount() != 2)
                System.out.println(format("a1: %s, a2: %s", account1.getAmount(), account2.getAmount()));
        }
    }

    private Runnable getRunnable(Account account1, Account account2) {
        return new Runnable() {
            @Override
            public void run() {
                synchronized (account1) {
                    synchronized (account2) {
                        if (account1.getAmount() > 0) {
                            account1.withdraw(1);
                            account2.add(1);
                        }
                    }
                }
            }
        };
    }
}
