package pl.edu.pwr.worker;

import pl.edu.pwr.properties.ConfigService;
import pl.edu.pwr.service.FileSender;
import pl.edu.pwr.service.FileWorker;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.util.List;
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

    public void listen(Path myDir){
        try {
            Boolean isFolder = (Boolean) Files.getAttribute(myDir,
                    "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException("Path: " + myDir + " is not a folder");
            }
        } catch (IOException ioe) {
            // Folder does not exists
            ioe.printStackTrace();
        }

        try {
            WatchService watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, ENTRY_CREATE);

            WatchKey watckKey = watcher.take();

            List<WatchEvent<?>> events = watckKey.pollEvents();
            for (WatchEvent event : events) {
                if (event.kind() == ENTRY_CREATE)
                    sendInThread(((WatchEvent<Path>) event).context().toString());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public void sendInThread(String path){
            pool.submit(new FileWorker(sender, path));
    }
}
