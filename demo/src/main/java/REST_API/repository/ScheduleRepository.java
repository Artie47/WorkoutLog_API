package REST_API.repository;

import REST_API.model.Schedule;
import java.util.Date;
import java.util.List;

import REST_API.model.Trainer;
import REST_API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public interface ScheduleRepository {

    List<Schedule> getLessonsOnMonth(User user);

}
