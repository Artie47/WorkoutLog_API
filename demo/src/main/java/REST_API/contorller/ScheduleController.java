package REST_API.contorller;


import REST_API.model.Schedule;
import REST_API.model.User;
import REST_API.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Utils utils;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @GetMapping("/schedule/getLessons")
    public List<Schedule> getLessons(@RequestBody String email, Date date){
        String sql = "SELECT * FROM schedule " +
                    "WHERE group_id = (SELECT group_id FROM users" +
                    "WHERE email = ?) and date_of_start BETWEEN ? AND ?";
        Date first_date = utils.getFirstDateOfMonth(date);
        Date second_date = utils.getLastDateOfMonth(date);
        List<Schedule> schedules = jdbcTemplate.query(sql, new Object[]{email, first_date, second_date},
                new RowMapper<Schedule>() {
                    @Override
                    public Schedule mapRow(ResultSet rs, int i) throws SQLException {
                        Schedule s = new Schedule();
                        try {
                            s.setStart_time(utils.ParseStringToDate(rs.getString("date_of_start")));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            s.setFinish_time(utils.ParseStringToDate(rs.getString("date_of_finish")));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        s.setId_group(Integer.parseInt(rs.getString("group_id")));
                        s.setId_trainer(Integer.parseInt(rs.getString("trainer_id")));
                        s.setId_sportkind(Integer.parseInt(rs.getString("portkind_id")));
                        return s;
                    }
                });
        return schedules;
    }
}
