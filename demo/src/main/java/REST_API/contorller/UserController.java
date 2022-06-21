package REST_API.contorller;

import REST_API.model.User;
import REST_API.repository.UserRepository;
import REST_API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;


    @PostMapping("/signIn")
    public User signIn(@RequestBody User authRequest) throws Exception {
        if(userRepository.findByEmail(authRequest.getEmail()) != null){
            return userRepository.findByEmail(authRequest.getEmail());
        }
        return authRequest;
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody User user) throws Exception{
        try {
            return userRepository.findByEmail(user.getEmail());
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

}
