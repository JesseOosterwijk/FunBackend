package Api.Service;

import Api.Entity.User;
import Api.JpaRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findbyEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }
    public User login(User user) {
        return new User();
    }

    public void createOrUpdateUser(User user) {
        try {
            userRepository.save(user);
        } catch(Exception e) {
            throw e;
        }
    }
}
