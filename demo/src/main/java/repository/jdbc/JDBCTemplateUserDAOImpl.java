package repository.jdbc;

import repository.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class JDBCTemplateUserDAOImpl implements UserDAO {

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
    public User getUserByEmail(String login) {
        String SQL = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{login} ,
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmailPassword(String Email, String Password) {
        String SQL = "SELECT * FROM users WHERE email = ? AND password = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{Email, Password},
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);

    }

    //Выдача пользователя по id
    @Override
    public User getUserById(int id) {
        String SQL = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(SQL,
                new Object[]{id} ,
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    //Создание пользователя
    @Override
    public void createUser(User user) {
        String SQL = "INSERT INTO USERS (name, login, password, date_of_reg, id_group) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(SQL,user.getName(), user.getEmail() ,user.getPassword(), user.getDate_of_reg(), user.getGroup());
    }

    //Удаление пользователя
    @Override
    public void removeUser(int id) {
        String SQL = "DELETE FROM USERS WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    //Изменение логина пользователя
    @Override
    public void updateUserEmail(int id, String email) {
        String SQL = "UPDATE USERS SET email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, email, id);
    }

    //Изменение пароля пользователя
    @Override
    public void updateUserPassword(User user, String password) {
        String SQL = "UPDATE USERS SET password = ? WHERE id = ?";
        jdbcTemplate.update(SQL, password, user.getId());
    }

    @Override
    public void save(User n) {

    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }
}

