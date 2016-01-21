package pl.edu.pwr.planner;

import pl.edu.pwr.msg.NoMessageTypeFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Categories {

    private static List<String> descriptions;

    static {
        descriptions = new ArrayList<>(Arrays.asList("TODAY", "TOMORROW", "NEXT_WEEK"));
    }

    public static String exists(String desc) throws NoMessageTypeFoundException {
        if(descriptions.contains(desc))
            return desc;
        throw new NoMessageTypeFoundException("category not found");
    }

    public static void add(String desc){
        descriptions.add(desc);
    }

    public static void print(){
        System.out.println("Available categories: ");
        for(String description : descriptions)
            System.out.println(description);
    }
}
