package REST_API.services;

import REST_API.model.Trainer;
import REST_API.model.User;
import REST_API.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomTrainerDetailsService implements UserDetailsService {
    @Autowired
    private TrainerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Trainer trainer = repository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(trainer.getEmail(), trainer.getPassword(), new ArrayList<>());
    }

}