package Api.Service;

import Api.Entity.User;
import Api.Repository.UserRepository;

import java.sql.SQLException;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) throws Exception {
            User user = userRepository.getUserByEmail(email);
            if(user.getPassword().equals(password)) {
                return user;
            } else {
            throw new Exception();
        }
    }

    public void register(String email, String username, String password) throws SQLException {
        try {
            userRepository.registerUser(email, username, password);
        } catch(Exception e) {
            throw e;
        }
    }
}
