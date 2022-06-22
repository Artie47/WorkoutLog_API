package REST_API.contorller;

import REST_API.model.*;
import REST_API.repository.UserRepository;
import REST_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    private Utils utils;

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

    @PostMapping("/getProfileData")
    public ProfileDTO getProfile(@RequestBody User user) throws Exception{

        Date date = new Date();

        ProfileDTO profileDTO = new ProfileDTO();

        String sql_getGroup = "SELECT * FROM groups " +
                    "WHERE id = (SELECT * FROM users where email = ?)";

        String sql_listOfDates = "SELECT * FROM schedule" +
                                "WHERE date_of_start BETWEEN ? and ? ";

        Date firstDate = utils.getFirstDateOfMonth(date);
        Date secondDate = utils.getLastDateOfMonth(date);

        String sql_getSportKind = "SELECT * FROM sportkinds" +
                                "WHERE id = (SELECT id FROM groups" +
                                "WHERE id = (SELECT group_id FROM users" +
                                "WHERE email = ?))";

        String sql_getAbonement = "SELECT date_of_reg FROM users" +
                                "WHERE email = ?";

        Group group = jdbcTemplate.queryForObject(sql_getGroup, new Object[]{user.getEmail()},
                new RowMapper<Group>() {
                    @Override
                    public Group mapRow(ResultSet rs, int i) throws SQLException {
                        Group s = new Group();
                        s.setName(rs.getString("name"));;
                        s.setPrice(Float.parseFloat(rs.getString("price")));
                        return s;
                    }
                });
        assert group != null;
        String groupName = group.getName();
        String groupPrice = String.valueOf(group.getPrice());

        List<Schedule> schedules = jdbcTemplate.query(sql_listOfDates, new Object[]{firstDate, secondDate},
                new RowMapper<Schedule>() {
                    @Override
                    public Schedule mapRow(ResultSet rs, int i) throws SQLException {
                        Schedule s = new Schedule();
                        try {
                            s.setStart_time(utils.ParseStringToDate(rs.getString("date_of_start")));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        return s;
                    }
                });

        List<Date> dates = utils.getListOfDates(schedules);
        dates = utils.cleanList(dates, date);
        String nexDateOfLesson = String.valueOf(dates.stream()
                .min(Date::compareTo)
                .get());

        SportKind sportKind = jdbcTemplate.queryForObject(sql_getSportKind, new Object[]{user.getEmail()},
                new RowMapper<SportKind>() {
                    @Override
                    public SportKind mapRow(ResultSet rs, int i) throws SQLException {
                        SportKind s = new SportKind();
                        s.setName(rs.getString("name"));;
                        return s;
                    }
                });
        assert sportKind != null;
        String sportkindName = sportKind.getName();

        profileDTO.setGroupName(groupName);
        profileDTO.setNextLesson(nexDateOfLesson);
        profileDTO.setNextLesson(nexDateOfLesson);
        profileDTO.setSportkind(sportkindName);
        profileDTO.setAbonement_time(utils.DateOfEndOfAbonement());

        return profileDTO;
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) {
        return userRepository.findByEmail(user.getEmail());
    }

    @GetMapping("/getAllGroups")
    public List<String> getAllGroups(){
        String sql = "SELECT name FROM groups";
        List<String> groups = jdbcTemplate.query(sql, new Object[]{},
                new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int i) throws SQLException {
                        Group s = new Group();
                        s.setName(rs.getString("name"));
                        return s.getName();
                    }
                });
        return groups;
    }

    @GetMapping("/getGroupId")
    public Integer getGroupId(@RequestBody String name){
        String sql = "SELECT id FROM groups" +
                "WHERE name = ?";

        Group group = jdbcTemplate.queryForObject(sql, new Object[]{name},
                new RowMapper<Group>() {
                    @Override
                    public Group mapRow(ResultSet rs, int i) throws SQLException {
                        Group s = new Group();
                        s.setId(Integer.parseInt(rs.getString("id")));
                        return s;
                    }
                });
        assert group != null;
        return group.getId();

    }

}
