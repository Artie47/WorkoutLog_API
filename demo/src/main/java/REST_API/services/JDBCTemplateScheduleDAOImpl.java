package REST_API.services;

import REST_API.model.Group;
import REST_API.model.Schedule;
import REST_API.model.Trainer;
import REST_API.model.User;
import REST_API.repository.ScheduleDAO;
import REST_API.repository.mappers.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JDBCTemplateScheduleDAOImpl implements ScheduleDAO {

    // Хранилище клиентов
    private static final Map<Integer, Schedule> SCHEDULE_REPOSITORY_MAP = new HashMap<>();

    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Выдача пользователя по логину
    @Override
    public Schedule getScheduleByDate(Date date) {
        String SQL = "SELECT * FROM schedule WHERE date = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{date} ,
                new BeanPropertyRowMapper<>(Schedule.class)
        ).stream().findAny().orElse(null);

    }

    //Выдача пользователя по id
    @Override
    public Schedule getScheduleById(int id) {
        String SQL = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{id} ,
                new BeanPropertyRowMapper<>(Schedule.class)
        ).stream().findAny().orElse(null);
    }

    //Создание пользователя
    @Override
    public void createSchedule(Schedule lesson) {
        String SQL = "INSERT INTO SCHEDULE (date, start_time, finish_time, id_sportKind, id_group, id_trainer) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(SQL, lesson.getDate(), lesson.getStartTime(), lesson.getFinishTime(), lesson.getId_sportkind(), lesson.getId_group(), lesson.getId_trainer());
    }

    //Удаление пользователя
    @Override
    public void removeSchedule(int id) {
        String SQL = "DELETE FROM SCHEDULE WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    //Изменение логина пользователя
    @Override
    public void updateScheduleDate(int id, Date date) {
        String SQL = "UPDATE SCHEDULE SET date = ? WHERE id = ?";
        jdbcTemplate.update(SQL, date, id);
    }

    //Изменение пароля пользователя
    @Override
    public void updateScheduleTrainer(int id, Trainer trainer) {
        String SQL = "UPDATE SCHEDULE SET trainer = ? WHERE id = ?";
        jdbcTemplate.update(SQL, trainer, id);
    }

    @Override
    public List<Schedule> getLessonsOnMonth(User user) {
        return null;
    }

//    @Override
//    public List<Schedule> getLessonsOnMonth(User user) {
//        Calendar cal = Calendar.getInstance();
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mi");
//
//        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
//        String firstDay = cal.getTime().toString();
//        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//        String endDay = cal.getTime().toString();
//
//
//        String SQL = String.format("SELECT * FROM SCHEDULE WHERE group_id = %d AND date_of_start BETWEEN %s AND %s", g.getId(), firstDay, endDay);
//        return jdbcTemplate.
//                query(SQL, new ScheduleMapper());

    }
