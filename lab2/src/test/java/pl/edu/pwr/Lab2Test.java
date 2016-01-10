package pl.edu.pwr;

import org.junit.Before;
import org.junit.Test;
import pl.edu.pwr.list.MyQueue;
import pl.edu.pwr.msg.Message;
import pl.edu.pwr.msg.NoMessageTypeFoundException;

import static org.junit.Assert.assertTrue;

public class Lab2Test {
    private MyQueue<Message> queue;

    @Before
    public void SetUp() throws NoMessageTypeFoundException {
        queue = new MyQueue<>(true);
        queue.enqueue(new Message("d1", "test1"));
        queue.enqueue(new Message("d2", "test2"));
        queue.enqueue(new Message("d3", "test3"));
    }

    @Test
    public void Lab2SendTest(){
        System.out.println("Sending message: " + queue.dequeue().toString());
    }

    @Test
    public void Lab2ReceiveTest() throws NoMessageTypeFoundException{
        Message msg = new Message("d3", "test4");
        System.out.println("Received message: " + msg.toString());
        queue.enqueue(msg);
    }
}
