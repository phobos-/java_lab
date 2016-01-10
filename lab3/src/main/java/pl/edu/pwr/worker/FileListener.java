package pl.edu.pwr.worker;

import pl.edu.pwr.properties.ConfigService;
import pl.edu.pwr.service.FileSender;
import pl.edu.pwr.service.FileWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by Pawel on 2014-12-05.
 */
public class FileListener {

    private ConfigService configService;

    private FileSender sender;

    private ExecutorService pool;

    public FileListener(ConfigService configService, FileSender sender, int threadAmount) {
        this.configService = configService;
        this.sender = sender;
        pool = Executors.newFixedThreadPool(threadAmount);
    }


    public void listen(){
//        try {
//            WatchService watcher = myDir.getFileSystem().newWatchService();
//            myDir.register(watcher, StandardWatchEventKind.ENTRY_CREATE);
//
//            WatchKey watckKey = watcher.take();
//
//            List<WatchEvent<?>> events = watckKey.pollEvents();
//            for (WatchEvent event : events) {
//                if (event.kind() == StandardWatchEventKind.ENTRY_CREATE)
//                    sendInThread();
//
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.toString());
//        }
    }

    public void sendInThread(String path){
            pool.submit(new FileWorker(sender, path));

    }
}
