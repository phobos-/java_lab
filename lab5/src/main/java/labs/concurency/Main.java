package labs.concurency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pawel on 26.11.15.
 */
public class Main {
    AtomicInteger a = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Account account1 = new Account("Jonh", 1);
            Account account2 = new Account("Michael", 1);

            Thread t = new Thread(getRunnable(account1, account2));
            Thread t2 = new Thread(getRunnable(account1, account2));

            t2.start();
            t.start();
            t.join();
            t2.join();

            if (account1.getAmount() != 0 || account2.getAmount() != 2) {
                System.out.println(account1.getAmount());
                System.out.println(account2.getAmount());
            }
        }
    }

    private static Runnable getRunnable(Account account1, Account account2) {
        return () -> {
            if (account1.getAmount() > 0) {
                account2.add(1);
                account1.withdraw(1);
            }
        };
    }


}
