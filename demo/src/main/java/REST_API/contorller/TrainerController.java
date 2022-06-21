package REST_API.contorller;

import REST_API.model.Trainer;
import REST_API.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    @PostMapping("/trainerSignIn")
    public Trainer trainerSignIn(@RequestBody Trainer authRequest) throws Exception {
        try {
            if (trainerRepository.findByEmail(authRequest.getEmail()) != null) {
                return trainerRepository.findByEmail(authRequest.getEmail());
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return authRequest;
    }

}
