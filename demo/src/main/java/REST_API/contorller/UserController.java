package REST_API.contorller;

import REST_API.model.User;
import REST_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/users/save")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(value = "/users/get")
    public Optional<User> getUser(@RequestBody Integer id){
        return userService.findById(id);
    }

//    @PostMapping(value = "/users/check")
//    public User check(@RequestBody User user){ return userService.findByEmailPassword(user);}

}