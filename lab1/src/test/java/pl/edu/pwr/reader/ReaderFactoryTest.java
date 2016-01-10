package pl.edu.pwr.reader;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static pl.edu.pwr.reader.ReaderFactory.create;

/**
 * Created by pawel on 30.10.15.
 */
public class ReaderFactoryTest {

    private static final String WEB_PATH = "http://google.com";
    private static final String FILE_PATH = "file";

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNullParam() {
        create(null);
    }

    @Test
    public void shouldReturnWebPageReader(){
        assertTrue(create(WEB_PATH) instanceof WebPageReader);
    }

    @Test
    public void shouldReturnFileReader(){
        assertTrue(create(FILE_PATH) instanceof FileReader);
    }
}