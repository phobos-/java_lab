package labs.concurency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pawel on 27.11.15.
 */
public class Executor {

    ExecutorService executor = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        new Executor().start();
    }

    private void start() {
        for (int i = 0; i < 10000; i++) {
            executor.submit(new WorkerClass(i));
        }
        System.out.println("done");

    }
}
