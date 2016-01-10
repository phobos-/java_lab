package pl.pwr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.pwr.examples.BaseUserDao;
import pl.pwr.user.User;

import java.sql.SQLException;

/**
 * Created by pawel on 10.12.15.
 */
@Component
public class DatabasesExample {

    @Autowired
    private BaseUserDao statementUserDao;

    @Autowired
    private BaseUserDao jdbcUserDao;

    @Autowired
    private MongoTemplate template;

    public void show() throws SQLException {
//        statementUserDao.getAll();
//        statementUserDao.insert(new User(1l, "saa"));
//        statementUserDao.getAll();
//        jdbcUserDao.getAll().forEach(System.out::println);
    template.findAll(User.class, "user");
    }
}
