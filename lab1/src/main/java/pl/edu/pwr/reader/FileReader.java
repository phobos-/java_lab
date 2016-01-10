package pl.edu.pwr.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Pawel on 2014-09-26.
 */
public class FileReader implements AbstractReader {

    private String path;

    public FileReader(String path) {
        this.path = path;
    }

    public String read() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Can not read file.");
            e.printStackTrace();
        }
        return new String(encoded);
    }
}
