package repository;

import model.User;
import javax.sql.DataSource;

public interface UserDAO {
    public void setDataSource(DataSource dataSource);
    public void createUser(User user);
    public User getUserByEmail(String email);
    public User getUserByEmailPassword(String Email, String Password);
    public User getUserById(int id);
    public void removeUser(int id);
    public void updateUserEmail(int id, String login);
    public void updateUserPassword(User user, String password);

    void save(User n);

    Iterable<User> findAll();
}
