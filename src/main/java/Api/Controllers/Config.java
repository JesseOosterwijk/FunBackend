package Api.Controllers;

import Api.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class Config {
    @Bean
    public Connection connection() throws SQLException {
        String connectionString = "jdbc:sqlserver://mssql.fhict.local/dbi398785_fundb;user=dbi398785;password=123;";
        Connection con = DriverManager.getConnection(connectionString);
        return con;
    }

    @Bean
    public UserRepository userRepository(Connection con) {
        return new UserRepository(con);
    }
}
