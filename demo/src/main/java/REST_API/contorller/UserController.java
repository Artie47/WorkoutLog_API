package REST_API.contorller;

import REST_API.model.User;
import REST_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(value = "/user/save")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping(value = "/users/check")
    public User check(@RequestBody User user){ return userRepository.findByEmailPassword(user.getEmail(), user.getPassword());}

}