package REST_API.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date start_time;
    private Date finish_time;
    private int id_group;
    private int id_sportkind;
    private int id_trainer;

}
