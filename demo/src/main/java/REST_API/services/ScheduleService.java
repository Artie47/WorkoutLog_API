package REST_API.services;

import REST_API.model.Schedule;
import REST_API.model.User;
import REST_API.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ScheduleService implements ScheduleRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Schedule> getLessonsOnMonth(User user) {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mi");

        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        String firstDay = cal.getTime().toString();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        String endDay = cal.getTime().toString();


        String SQL = String.format("SELECT * FROM SCHEDULE WHERE group_id = %d AND date_of_start BETWEEN %s AND %s", user.getGroup_id(), firstDay, endDay);
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Schedule.class));

    }

}