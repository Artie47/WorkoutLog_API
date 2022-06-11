package REST_API.repository.mappers;

import REST_API.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("ID"));
        schedule.setStart_time(rs.getDate("DATE_OF_START"));
        schedule.setFinish_time(rs.getDate("DATE_OF_FINISH"));
        schedule.setId_group(rs.getObject("GROUP_ID", Group.class));
        schedule.setId_sportkind(rs.getObject("SPORTKIND_ID", SportKind.class));
        schedule.setId_trainer(rs.getObject("TRAINER_ID", Trainer.class));
        return schedule;
    }
}