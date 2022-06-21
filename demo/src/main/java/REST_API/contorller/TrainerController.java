package REST_API.contorller;

import REST_API.model.Trainer;
import REST_API.repository.TrainerRepository;
import REST_API.repository.UserRepository;
import REST_API.services.CustomTrainerDetailsService;
import REST_API.services.CustomUserDetailsService;
import REST_API.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TrainerController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomTrainerDetailsService customTrainerDetailsService;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/trainerSignIn")
    public Trainer trainerSignIn(@RequestBody Trainer authRequest) throws Exception {
        try {
            if (customTrainerDetailsService.loadUserByUsername(authRequest.getEmail()) != null) {
                return trainerRepository.findByEmail(customTrainerDetailsService.loadUserByUsername(authRequest.getEmail()).getUsername());
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return authRequest;
    }

}
