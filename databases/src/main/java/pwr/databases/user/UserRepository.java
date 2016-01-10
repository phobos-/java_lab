package pwr.databases.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawel on 07.12.15.
 */

@Repository
public class UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("u1", "u1", "u1"));
        users.add(new User("u2", "2", "u2"));
        users.add(new User("u3", "u3", "u3"));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User u) {
        users.add(u);
    }
}
