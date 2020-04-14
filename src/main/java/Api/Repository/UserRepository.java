package Api.Repository;

import Api.Entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private JdbcTemplate connection;

    public UserRepository(JdbcTemplate con) {
        this.connection = con;
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>();
    }

    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM [User] WHERE [E-mail] = ?";
        List<User> statement = connection.query(query, new BeanPropertyRowMapper<User>(), email);

        return statement.get(0);
    }

    public void registerUser(String email, String username, String password) throws SQLException {
        String query = "INSERT INTO [User] ([E-mail], [Name], [Password]) VALUES (?, ?, ?)";
        connection.update(query, email, username, password);
    }
}
