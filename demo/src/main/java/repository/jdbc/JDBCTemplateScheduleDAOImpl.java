package repository.jdbc;

import repository.ScheduleDAO;
import model.Schedule;
import model.Trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import  java.util.Date;

@Component
public class JDBCTemplateScheduleDAOImpl implements ScheduleDAO {

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
        String SQL = "SELECT * FROM trainers WHERE id = ?";
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
}
