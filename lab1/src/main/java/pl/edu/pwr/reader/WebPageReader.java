package pl.edu.pwr.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Pawel on 2014-09-26.
 */
public class WebPageReader implements AbstractReader{

    private String path;

    public WebPageReader(String path) {
        if(path.startsWith("www"))
            this.path = "http://" + path;
        else this.path = path;
    }

    public String read() {
        StringBuilder b = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(path).openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine);
        } catch (Exception e) {
            System.out.println("Can not read URL");
            e.printStackTrace();
        }
        return b.toString();
    }
}
