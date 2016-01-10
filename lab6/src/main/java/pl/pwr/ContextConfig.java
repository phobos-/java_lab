package pl.pwr;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pawel on 10.12.15.
 */
@Configuration
@ComponentScan("pl.pwr")
@PropertySource("classpath:app.properties")
public class ContextConfig {

    @Autowired
    private Environment environment;

    public static final String DRIVER = "org.postgresql.Driver";

    @Bean(destroyMethod = "close")
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection("url", "usr", "pwd");
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DRIVER);
        ds.setUrl("url");
        ds.setUsername("usr");
        ds.setPassword("pwd");
        return ds;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
//        MongoCredential credential = createCredential(config.get(MONGO_USERNAME), config.get(MONGO_DATABASE), config.get(MONGO_PASSWORD).toCharArray());
        MongoClient c = new MongoClient(new ServerAddress("localhost", 27017));
        return new MongoTemplate(new SimpleMongoDbFactory(c, "test"));
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
