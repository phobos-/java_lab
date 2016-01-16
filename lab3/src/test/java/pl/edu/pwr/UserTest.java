package pl.edu.pwr;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserTest {
    @Test
    public void userCreateTest(){
        User user = new User("'",1);
        assertTrue(!user.equals(null));
    }

    @Test
    public void userGetHeaderTest(){
        User user = new User("'",1);
        assertTrue(user.getHeader().contains("id"));
    }

    @Test
    public void userEqualsTest(){
        User user = new User("'",1);
        assertTrue(user.equals(new User("test",1)));
    }

    @Test
    public void userHashcodeTest(){
        User user = new User("'",1);
        assertTrue((Integer)user.hashCode() != null);
    }

    @Test
    public void userToStringTest(){
        User user = new User("test",1);
        assertTrue(user.toString().contains("test"));
        assertTrue(user.toString().contains("1"));
    }
}
