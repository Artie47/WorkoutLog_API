package model;

import javax.persistence.*;
@Entity
@Table(name = "Groups")
public class SportKind {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
}
