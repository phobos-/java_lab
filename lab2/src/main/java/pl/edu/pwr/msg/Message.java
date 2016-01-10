package pl.edu.pwr.msg;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private static AtomicInteger idCounter = new AtomicInteger();
    private int id;
    private Type priority;
    private String msg;

    public Message(){
        this.id = idCounter.getAndIncrement();
        this.priority = Type.LOW;
        this.msg = "";
    }

    public Message(String priority, String desc ) throws NoMessageTypeFoundException {
        this.id = idCounter.getAndIncrement();
        this.priority = Type.getByDesc(priority);
        this.msg = desc;
    }

    public String toString(){
        return "Id: " + id + " Priority: " + priority.toString() + " Message: " + msg;
    }
}
