package pl.pwr.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.pwr.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawel on 10.12.15.
 */
@Repository

public class StatementUserDao implements BaseUserDao {

    @Autowired
    private Connection connection;


    @Override
    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(USER_SELECT);
            while (rs.next()) {
                User e = new User(rs.getLong("id"), rs.getString("username"));
                users.add(e);
                System.out.println(e);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public void insert(User u) {
        try {
            connection.createStatement()
                    .execute("INSERT INTO USERS VALUES(" + u.getId() + ",'" + u.getUsername()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
