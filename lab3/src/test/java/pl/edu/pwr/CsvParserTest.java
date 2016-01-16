package pl.edu.pwr;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CsvParserTest {
    @Test
    public void testValidateNotFull(){
        CsvParser parser = new CsvParser("'");
        assertTrue(!parser.validate(new String[]{"",""}));
    }

    @Test
    public void testValidate(){
        CsvParser parser = new CsvParser("'");
        assertTrue(parser.validate(new String[]{"","","","","","test@example.com","plus","1"}));
    }

    @Test
    public void testValidateWrongMail(){
        CsvParser parser = new CsvParser("'");
        assertTrue(!parser.validate(new String[]{"","","","","","testmail","plus","1"}));
    }

    @Test
    public void testValidateWrongProvider(){
        CsvParser parser = new CsvParser("'");
        assertTrue(!parser.validate(new String[]{"","","","","","test@example.com","$#","1"}));
    }

    @Test
    public void testValidateWrongSalary(){
        CsvParser parser = new CsvParser("'");
        assertTrue(!parser.validate(new String[]{"","","","","","test@example.com","plus","#$"}));
    }
}
