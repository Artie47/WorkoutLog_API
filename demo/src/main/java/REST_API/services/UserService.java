package REST_API.services;

import REST_API.model.User;
import REST_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  UserRepository userRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

//    @Override
//    public User findByEmailPassword(User user) {
//        String SQL = "SELECT * FROM users WHERE email = ? AND password = ?";
//
//        return jdbcTemplate.query(SQL,
//                new Object[]{user.getEmail(), user.getPassword()},
//                new BeanPropertyRowMapper<>(User.class)
//        ).stream().findAny().orElse(null);
//    }


    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

}



