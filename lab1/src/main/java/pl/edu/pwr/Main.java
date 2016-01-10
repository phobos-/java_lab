package pl.edu.pwr;

import pl.edu.pwr.reader.AbstractReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.edu.pwr.config.Keys.*;
import static pl.edu.pwr.reader.ReaderFactory.create;

public class Main {
    public static final int PROPERTIES_FILE = 0;

    static Properties testProperties = new Properties();

    public static void main(String[] args) {
        run(args);
    }

    public static void run(String[] args) {
        try {
            testProperties.load(new FileInputStream(args[PROPERTIES_FILE]));
            AbstractReader reader = create(testProperties.getProperty(INPUT));
            String body = reader.read();

            Pattern linkPattern = Pattern.compile("<a[^>]+href=[\\\"']?([^\\\"']+)[\"']?[^>]*>(.+?)</a>",
                                                                               Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            Matcher pageMatcher = linkPattern.matcher(body);
            ArrayList<String> links = new ArrayList<>();
            ArrayList<String> domainLinks = new ArrayList<>();
            while(pageMatcher.find()){
                links.add(pageMatcher.group(1));
            }
            System.out.println("Found " + links.size() + " links.");
            links.stream().filter(l -> l.startsWith("/")).forEach(domainLinks::add);

            if(testProperties.getProperty(VIEW_LINKS).equals("true"))
                if (testProperties.getProperty(DOMAIN_ONLY).equals("true"))
                    domainLinks.forEach(link -> System.out.println(link));
                else links.forEach(link -> System.out.println(link));

            if(testProperties.getProperty(VIEW_SOURCE).equals("true"))
                if (testProperties.getProperty(LINK) == null || testProperties.getProperty(LINK).isEmpty()) {
                    System.out.println("View page body for: " + testProperties.getProperty(INPUT));
                    System.out.println(body);
                } else {
                    String newlink = testProperties.getProperty(INPUT)
                            + domainLinks.get(Integer.parseInt(testProperties.getProperty(LINK)));
                    System.out.println("View page body for: " + newlink);
                    reader = create(newlink);
                    System.out.println(reader.read());
                }

        } catch (IOException e) {
            System.out.println("no file");
        }
    }
}
