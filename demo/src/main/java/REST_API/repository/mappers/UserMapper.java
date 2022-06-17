package REST_API.repository.mappers;

import REST_API.DTO.UserDTO;
import REST_API.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        Date date_of_reg = user.getDate_of_reg();

        return new UserDTO(name, email, password, date_of_reg);
    }

    public User toUser(UserDTO userDTO){
        String name = userDTO.getName();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        Date date_of_reg = userDTO.getDate_of_reg();

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setDate_of_reg(date_of_reg);

        return user;
    }
}
