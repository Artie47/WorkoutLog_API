package REST_API.contorller;

import REST_API.model.AuthRequest;
import REST_API.model.User;
import REST_API.repository.UserRepository;
import REST_API.services.CustomUserDetailsService;
import REST_API.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signIn")
    public String signIn(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Неверный пароль/эл. почта");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) throws Exception {
        try {
            if (customUserDetailsService.loadUserByUsername(user.getEmail()) == null) {
                return userRepository.save(user);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return user;
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
