package labs.concurency;

import java.util.concurrent.*;

/**
 * Created by pawel on 26.11.15.
 */
public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new FutureExample().start();


    }

    private void start() throws ExecutionException, InterruptedException {
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(10000);
                return null;
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
        executor.shutdownNow();
        future.get();

    }

}
