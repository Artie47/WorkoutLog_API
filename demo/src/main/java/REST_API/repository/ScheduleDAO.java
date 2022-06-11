package REST_API.repository;

import REST_API.model.Schedule;
import java.util.Date;
import java.util.List;

import REST_API.model.Trainer;
import REST_API.model.User;

import javax.sql.DataSource;

public interface ScheduleDAO {
    public void setDataSource(DataSource dataSource);
    public void createSchedule(Schedule schedule);
    public Schedule getScheduleByDate(Date date);
    public Schedule getScheduleById(int id);
    public void removeSchedule(int id);
    public void updateScheduleDate(int id, Date date);
    public void updateScheduleTrainer(int id, Trainer trainer);

    List<Schedule> getLessonsOnMonth(User user);
}
