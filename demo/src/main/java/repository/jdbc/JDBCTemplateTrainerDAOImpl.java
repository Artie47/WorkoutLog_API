package repository.jdbc;

import repository.TrainerDAO;
import model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class JDBCTemplateTrainerDAOImpl implements TrainerDAO {

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
    public Trainer getTrainerByEmail(String login) {
        String SQL = "SELECT * FROM trainers WHERE email = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{login} ,
                new BeanPropertyRowMapper<>(Trainer.class)
        ).stream().findAny().orElse(null);

    }

    //Выдача пользователя по id
    @Override
    public Trainer getTrainerById(int id) {
        String SQL = "SELECT * FROM trainers WHERE id = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{id} ,
                new BeanPropertyRowMapper<>(Trainer.class)
        ).stream().findAny().orElse(null);
    }

    //Создание пользователя
    @Override
    public void createTrainer(Trainer user) {
        String SQL = "INSERT INTO TRAINERS (name, email, password, id_sportKind) VALUES (?,?,?,?)";
        jdbcTemplate.update(SQL,user.getName(), user.getEmail() ,user.getPassword(), user.getSportKind());
    }

    //Удаление пользователя
    @Override
    public void removeTrainer(int id) {
        String SQL = "DELETE FROM TRAINERS WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    //Изменение логина пользователя
    @Override
    public void updateTrainerEmail(int id, String email) {
        String SQL = "UPDATE USERS SET email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, email, id);
    }

    //Изменение пароля пользователя
    @Override
    public void updateTrainerPassword(Trainer user, String password) {
        String SQL = "UPDATE USERS SET password = ? WHERE id = ?";
        jdbcTemplate.update(SQL, password, user.getId());
    }
}

