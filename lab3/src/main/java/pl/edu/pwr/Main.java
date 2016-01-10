package pl.edu.pwr;

import java.io.IOException;

/**
 * Created by Pawel on 2014-12-05.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("main thread start");
        Thread t = new Thread(() -> {
            System.out.println("runnable 1");
        }) ;
        Thread t2 = new Thread(() -> {
            System.out.println("runnable 2");
        }) ;
        Thread t3 = new Thread(() -> {
            System.out.println("runnable 3");
        }) ;

        t.start();
        t2.start();
        t3.start();

        t.join();
        t2.join();
        t3.join();
        System.out.println("main thread finish");
    }
}
