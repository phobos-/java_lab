package pl.edu.pwr;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IdParserTest {
    @Test
    public void testParse(){
        IdParser parser = new IdParser();
        assertTrue(parser.parse("10").isPresent());
    }

    @Test
    public void testParseFail(){
        IdParser parser = new IdParser();
        assertTrue(!parser.parse("%$").isPresent());
    }
}
