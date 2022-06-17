package REST_API.repository;

import REST_API.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
