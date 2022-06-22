package REST_API.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    private int id_sport_kind;


}
