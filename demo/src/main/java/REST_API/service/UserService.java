package REST_API.service;

import REST_API.exceptions.NoValidLoginException;
import REST_API.exceptions.NoValidPasswordException;
import REST_API.exceptions.UserAlreadyExistException;
import REST_API.exceptions.UserNoExistException;
import REST_API.model.User;
import REST_API.repository.jdbc.JDBCTemplateUserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JDBCTemplateUserDAOImpl jdbcTemplateUserDaoImpl;

    //Авторизация
    public User authorization(User user2) throws UserNoExistException {
        try{
            User user = jdbcTemplateUserDaoImpl.getUserByEmail(user2.getEmail());
            if(user.getPassword().equals(user2.getPassword()))
                return user;
        }
        catch (Exception e){
            throw new UserNoExistException("User not exist(p)");
        }
        return null;
    }

    //Создание пользователя(регистрация)
    public void registration(User user) throws UserAlreadyExistException {
        if (jdbcTemplateUserDaoImpl.getUserByEmail(user.getEmail()) !=null)
            throw new UserAlreadyExistException("User with this login already exist!");
        else
            jdbcTemplateUserDaoImpl.createUser(user);
    }

    //Удаление аккаунта пользователя
    public void delete(int id) throws UserNoExistException {
        if(jdbcTemplateUserDaoImpl.getUserById(id) == null)
            throw new UserNoExistException("User with this id no exists!");
        else
            jdbcTemplateUserDaoImpl.removeUser(id);
    }

    //Обновление пароля пользователя
    public void updatePassword(String login, String validPassword, String newPassword) throws NoValidPasswordException {
        if(!jdbcTemplateUserDaoImpl.getUserByEmail(login).getPassword().equals(validPassword))
            throw new NoValidPasswordException("No valid password!");
        else
            jdbcTemplateUserDaoImpl.updateUserPassword(jdbcTemplateUserDaoImpl.getUserByEmail(login),
                    newPassword);
    }

    //Обновление логина пользователя
    public void updateLogin(int id, String validLogin, String newLogin) throws NoValidLoginException {
        if(jdbcTemplateUserDaoImpl.getUserByEmail(validLogin ) == null)
            throw new NoValidLoginException("No valid login!");
        else
            jdbcTemplateUserDaoImpl.updateUserEmail(id, newLogin);
    }
}

