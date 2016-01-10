package pl.edu.pwr.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MyListTest {

    @Test(expected=IndexOutOfBoundsException.class)
    public void testMyList() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        assertTrue(4 == list.get(4));
        assertTrue(2 == list.get(1));
        assertTrue(3 == list.get(2));

        list.get(6);
    }


    @Test(expected=IndexOutOfBoundsException.class)
    public void testNegative() {
        MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.get(-1);
    }

    @Test
    public void testEmptyClear(){
        MyList<Integer> list = new MyList<>();
        list.add(1);
        assertTrue(1 == list.size());
        assertTrue(list.contains(1));
        list.clear();
        assertTrue(list.isEmpty());
    }
}