package repository;

import model.Schedule;
import java.util.Date;
import model.SportKind;
import model.Trainer;

import javax.sql.DataSource;

public interface ScheduleDAO {
    public void setDataSource(DataSource dataSource);
    public void createSchedule(Schedule schedule);
    public Schedule getScheduleByDate(Date date);
    public Schedule getScheduleById(int id);
    public void removeSchedule(int id);
    public void updateScheduleDate(int id, Date date);
    public void updateScheduleTrainer(int id, Trainer trainer);
}
