package REST_API.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Float price;
    private int id_trainer;
}
