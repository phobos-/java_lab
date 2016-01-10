package pl.pwr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

/**
 * Created by pawel on 10.12.15.
 */
public class Application {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ContextConfig.class);
        ctx.getBean(DatabasesExample.class).show();
    }
}
