package REST_API.repository;

import REST_API.model.Schedule;
import java.util.Date;
import java.util.List;

import REST_API.model.Trainer;
import REST_API.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.sql.DataSource;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    Schedule getScheduleByDate(Date date);

    //Выдача пользователя по id
    Schedule getScheduleById(int id);

    //Создание пользователя
    void createSchedule(Schedule lesson);

    //Удаление пользователя
    void removeSchedule(int id);

    //Изменение логина пользователя
    void updateScheduleDate(int id, Date date);

    //Изменение пароля пользователя
    void updateScheduleTrainer(int id, Trainer trainer);

    List<Schedule> getLessonsOnMonth(User user);
}
