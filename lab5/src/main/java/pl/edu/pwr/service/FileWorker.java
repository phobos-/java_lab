package pl.edu.pwr.service;

import java.util.concurrent.Callable;

/**
 * Created by Pawel on 2014-12-05.
 */
public class FileWorker implements Callable{

    private FileSender fileSender;
    String  path;
    public FileWorker(FileSender fileSender, String path) {
        this.fileSender = fileSender;
        this.path = path;
    }

    public void setPath (String path){
        this.path = path;
    }

    @Override
    public Void call() throws Exception {
        fileSender.send(path);
        return null;
    }
}
