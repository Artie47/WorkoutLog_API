package REST_API.contorller;

import REST_API.model.Schedule;
import REST_API.repository.GroupDAO;
import REST_API.repository.ScheduleDAO;
import REST_API.repository.UserRepository;
import REST_API.model.User;
import REST_API.repository.UserRepository;
import REST_API.services.JDBCTemplateUserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/user/save")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }



}