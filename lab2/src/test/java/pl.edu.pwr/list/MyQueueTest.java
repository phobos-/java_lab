package pl.edu.pwr.list;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyQueueTest {

    @Test
    public void testMyQueueFIFO(){
        MyQueue<Integer> queue = new MyQueue<>(true);
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(1==queue.dequeue());
    }

    @Test
    public void testMyQueueLIFO(){
        MyQueue<Integer> queue = new MyQueue<>(false);
        queue.enqueue(1);
        queue.enqueue(2);
        assertTrue(2==queue.dequeue());
    }

    @Test
    public void testSend(){
    }
}