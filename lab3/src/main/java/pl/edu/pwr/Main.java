package pl.edu.pwr;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static pl.edu.pwr.properties.Keys.*;

/**
 * Created by Pawel on 2014-12-05.
 */
public class Main {
    public static final int PROPERTIES_FILE = 0;

    static Properties testProperties = new Properties();
    static FileCreator creator;

    public static void main(String[] args) throws IOException, InterruptedException {
        testProperties.load(new FileInputStream(args[PROPERTIES_FILE]));
        creator = new FileCreator(testProperties.getProperty(DELIMITER),
                                  testProperties.getProperty(OUTPUT),
                                  testProperties.getProperty(ERRORS),
                                  Integer.parseInt(testProperties.getProperty(ROWSPERFILE)));
        creator.writeFiles(new CsvParser(creator.getDelimiter()).parse(testProperties.getProperty(FILENAME)));
    }
}
