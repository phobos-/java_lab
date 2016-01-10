package pl.edu.pwr.list;

import java.util.LinkedList;

public class MyQueue<E> {
    private LinkedList<E> list = new LinkedList<E>();
    private boolean isFIFO = true;

    public MyQueue(boolean isFIFO){
        this.isFIFO = isFIFO;
    }

    public MyQueue(){
        this.isFIFO = true;
    }

    public void enqueue(E item) {
        if(isFIFO) list.addLast(item);
        else list.addFirst(item);
    }

    /* removes the head (first element) */
    public E dequeue() {
        return list.poll();
    }

    public boolean hasItems() {
        return !list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void addItems(MyQueue<? extends E> q) {
        while (q.hasItems())
            if(isFIFO) list.addLast(q.dequeue());
            else list.addFirst(q.dequeue());
    }
}

