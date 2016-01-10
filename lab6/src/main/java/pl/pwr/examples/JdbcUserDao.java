package pl.pwr.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pwr.user.User;
import pl.pwr.user.UserMapper;

import java.util.List;

/**
 * Created by pawel on 10.12.15.
 */
@Repository
public class JdbcUserDao implements BaseUserDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<User> getAll() {
        return template.query(USER_SELECT, new UserMapper());
    }

    @Override
    public void insert(User u) {

    }
}
