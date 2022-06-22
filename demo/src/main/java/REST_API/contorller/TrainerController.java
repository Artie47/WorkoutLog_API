package REST_API.contorller;

import REST_API.model.Trainer;
import REST_API.model.User;
import REST_API.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class TrainerController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TrainerRepository trainerRepository;

    @PostMapping("/trainerSignIn")
    public Trainer signIn(@RequestBody Trainer authRequest) throws Exception {
        String sql = "SELECT * FROM trainers WHERE email = ? AND password = ?";
        Trainer trainer = jdbcTemplate.queryForObject(sql, new Object[]{authRequest.getEmail(), authRequest.getPassword()},
                new RowMapper<Trainer>() {
                    @Override
                    public Trainer mapRow(ResultSet rs, int i) throws SQLException {
                        Trainer t = new Trainer();
                        t.setName(rs.getString("name"));
                        t.setEmail(rs.getString("email"));
                        t.setId_sport_kind(Integer.parseInt(rs.getString("sportkind_id")));
                        t.setPassword(rs.getString("password"));
                        return t;
                    }
                });
        return trainer;
    }
    @PostMapping("/trainerSignUp")
    public Trainer signUp(@RequestBody Trainer user) {
        return trainerRepository.save(user);
    }

}
