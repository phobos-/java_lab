package pl.edu.pwr.service;

import com.dropbox.core.*;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: pszczerbicki
 */
public class FileSender {

    AtomicInteger stats = new AtomicInteger(0);
    private String accessToken;

    public FileSender(String accessToken){
        this.accessToken = accessToken;
    }
    public void send(String path){
        sendFile(new File(path));
    }

    public int getStats(){
       return stats.getAndSet(0);
    }

    private void sendFile(File f){
        stats.incrementAndGet();
        System.out.println("new file " + f.getName());
        try {
            DbxClient client = new DbxClient(new DbxRequestConfig("JavaTutorial/1.0",
                    Locale.getDefault().toString()), accessToken);

            System.out.println("Linked account: " + client.getAccountInfo().displayName);

            FileInputStream inputStream = new FileInputStream(f);
            try {
                DbxEntry.File uploadedFile = client.uploadFile(f.getName(),
                        DbxWriteMode.add(), f.length(), inputStream);
                System.out.println("Uploaded: " + uploadedFile.toString());
            } finally {
                inputStream.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
