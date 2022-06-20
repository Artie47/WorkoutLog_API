package REST_API.repository;

import REST_API.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    User findByEmailPassword(User user);

}
