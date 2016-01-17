package pl.edu.pwr;

import com.dropbox.core.*;
import pl.edu.pwr.properties.ConfigService;
import pl.edu.pwr.service.FileSender;
import pl.edu.pwr.service.FileWorker;
import pl.edu.pwr.service.StatsService;
import pl.edu.pwr.worker.FileListener;
import pl.edu.pwr.worker.StatsWorker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import static pl.edu.pwr.properties.Keys.*;

/**
 * Created by Pawel on 2014-12-05.
 */
public class Main {
    static ConfigService configService;
    static FileSender fileSender;
    static FileListener listener;
    static StatsWorker statsWorker;
    static StatsService statsService;
    static FileWorker fileWorker;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "INSERT_APP_KEY";
        final String APP_SECRET = "INSERT_APP_SECRET";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
                Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
        System.out.println("1. Go to: " + authorizeUrl);
        System.out.println("2. Click \"Allow\" (you might have to log in first)");
        System.out.println("3. Copy the authorization code.");
        String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

        // This will fail if the user enters an invalid authorization code.
        DbxAuthFinish authFinish = null;
        try {
            authFinish = webAuth.finish(code);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        String accessToken = authFinish.accessToken;

        configService = new ConfigService("lab5/properties.txt");
        fileSender = new FileSender(accessToken);
        listener = new FileListener(configService, fileSender, Integer.parseInt(configService.getProp(THREADS_AMOUNT)));
        statsService = new StatsService(fileSender);
        statsWorker = new StatsWorker(statsService);
        fileWorker = new FileWorker(fileSender, null);

        Path myDir = Paths.get(configService.getProp(INPUT_DIR));

        Thread t = new Thread(() -> {
            while(true){
                statsWorker.generateStats();
                try {
                    Thread.sleep(Integer.parseInt(configService.getProp(REFRESH_RATE)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) ;

        Thread t2 = new Thread(() -> {
            while(true){
                listener.listen(myDir);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) ;

        Thread t3 = new Thread(() -> {
            for(File file : myDir.toFile().listFiles()) {
                fileWorker.setPath(file.getPath());
                try {
                    fileWorker.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) ;

        t.start();
        t2.start();
        t3.start();
    }
}
