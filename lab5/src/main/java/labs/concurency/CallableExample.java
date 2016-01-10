package labs.concurency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by pawel on 26.11.15.
 */
public class CallableExample {

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        new CallableExample().start();
    }

    private void start() throws InterruptedException {

        IntStream.rangeClosed(1, 1000).boxed().forEach(s->{
            executor.submit(new WorkerClass(s));
        });


        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
    }
}
