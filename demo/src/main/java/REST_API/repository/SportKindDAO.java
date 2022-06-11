package REST_API.repository;

import REST_API.model.SportKind;
import javax.sql.DataSource;

public interface SportKindDAO {
    public void setDataSource(DataSource dataSource);
    public void createSportKing(SportKind sportkind);
    public SportKind getSportKingByEmail(String sportkind);
    public SportKind getSportKindById(int id);
    public void removeSportKind(int id);
    public void updateSportKindEmail(int id, String email);
    public void updateSportKindPassword(SportKind sk, String password);
}
