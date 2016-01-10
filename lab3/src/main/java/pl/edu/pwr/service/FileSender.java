package pl.edu.pwr.service;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: pszczerbicki
 */
public class FileSender {

    AtomicInteger stats = new AtomicInteger(0);
    public void send(String path){
        sendFile(new File(path));
        stats.incrementAndGet();
    }

    public int getStats(){
       return stats.getAndSet(0);
    }

    private void sendFile(File f){
     stats.incrementAndGet();
        System.out.println("new file " + f.getName());
        //TODO send file
      }



}
