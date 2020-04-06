package Api.Repository;

import Api.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {

    private Connection connection;

    public UserRepository(Connection con) {
        this.connection = con;
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>();
    }

    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM [User] WHERE Email = ?";
        return new User();

    }
}
