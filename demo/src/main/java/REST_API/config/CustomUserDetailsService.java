package REST_API.config;


import REST_API.model.User;
import REST_API.repository.jdbc.JDBCTemplateUserDAOImpl;
import REST_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService{
    @Autowired
    private UserService userService;

    @Autowired
    private JDBCTemplateUserDAOImpl jdbcTemplateUserDaoImpl;

    public CustomUserDetails loadUserByUsername(String username){
        User user = jdbcTemplateUserDaoImpl.getUserByEmail(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }

    /*
     public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
     */
}
