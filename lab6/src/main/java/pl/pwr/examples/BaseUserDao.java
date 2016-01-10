package pl.pwr.examples;

import pl.pwr.user.User;

import java.util.List;

/**
 * Created by pawel on 10.12.15.
 */
public interface BaseUserDao {
    String USER_SELECT = "SELECT * FROM USERS";

    List<User> getAll();

    void insert(User u);
}
