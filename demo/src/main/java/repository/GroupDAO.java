package repository;

import model.Group;
import javax.sql.DataSource;

public interface GroupDAO {
    public void setDataSource(DataSource dataSource);
    public void createGroup(Group group);
    public Group getGroupByEmail(String group);
    public Group getGroupById(int id);
    public void removeGroup(int id);
    public void updateGroupEmail(int id, String login);
    public void updateGroupPassword(Group group, String password);
}
