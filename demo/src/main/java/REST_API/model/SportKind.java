package REST_API.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "Groups")
public class SportKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
