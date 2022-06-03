package repository;

import model.Trainer;
import javax.sql.DataSource;

public interface TrainerDAO {
    public void setDataSource(DataSource dataSource);
    public void createTrainer(Trainer trainer);
    public Trainer getTrainerByEmail(String email);
    public Trainer getTrainerById(int id);
    public void removeTrainer(int id);
    public void updateTrainerEmail(int id, String login);
    public void updateTrainerPassword(Trainer trainer, String password);
}
