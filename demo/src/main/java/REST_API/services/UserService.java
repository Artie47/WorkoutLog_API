package REST_API.services;

import REST_API.model.User;
import REST_API.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    JdbcTemplate jdbcTemplate;

    public User findByEmail(String email) {
        return new User();
    }

    public User reg(User user) {
        return userRepository.save(user);
    }
}



