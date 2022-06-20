package REST_API.model;

import javax.persistence.*;
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private Trainer id_trainer;

}
