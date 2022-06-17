package REST_API.services;

import REST_API.repository.UserRepository;
import REST_API.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class JDBCTemplateUserDAOImpl  {

    @Autowired
    private UserRepository userRepository;


    public User save(User user){
        return userRepository.save(user);
    }

}



