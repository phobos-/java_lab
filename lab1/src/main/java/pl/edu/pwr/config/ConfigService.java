package pl.edu.pwr.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

    private Properties p = new Properties();

    public ConfigService(String path) {
        try {
            p.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("File not found due to: " + e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    public ConfigService(Properties p) {
        this.p = p;
    }

    public String getProperty(String key) {
       return p.getProperty(key);
    }
}
