package labs.concurency;

import java.util.concurrent.Callable;

/**
 * Created by pawel on 26.11.15.
 */
public class WorkerClass implements Callable<Void> {

    private Integer jobNum;

    public WorkerClass(Integer jobNum) {
        this.jobNum = jobNum;
    }

    @Override
    public Void call() throws Exception {
        System.out.println(String.format("Job: %s, thread: %s", jobNum, Thread.currentThread().getId()));
        Thread.sleep(1000);
        return null;
    }
}
