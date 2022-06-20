package REST_API.repository;

import REST_API.model.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByEmail(String email);

}