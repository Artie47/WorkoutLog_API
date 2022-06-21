package REST_API.contorller;

import REST_API.Application;
import REST_API.Encoder;
import REST_API.model.User;
import REST_API.repository.UserRepository;
import REST_API.services.UserService;
import org.graalvm.compiler.replacements.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@Component
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;



    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @PostMapping("/signIn")
    public User signIn(@RequestBody User authRequest) throws Exception {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{authRequest.getEmail(), authRequest.getPassword()},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int i) throws SQLException {
                        User s = new User();
                        s.setName(rs.getString("name"));
                        s.setEmail(rs.getString("email"));
                        s.setPassword(rs.getString("password"));
                        s.setDate_of_reg(rs.getString("date_of_reg"));
                        s.setGroup_id(Integer.parseInt(rs.getString("group_id")));
                        return s;
                    }
                });
        return user;
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        return userRepository.findByEmail(user.getEmail());
    }

}
