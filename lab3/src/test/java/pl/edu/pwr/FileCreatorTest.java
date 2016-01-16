package pl.edu.pwr;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FileCreatorTest {
    @Test
    public void testDelimiter(){
        FileCreator creator = new FileCreator("test","","",1);
        assertTrue(creator.getDelimiter().contains("test"));
    }
}
