package REST_API.model;

import javax.persistence.*;
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_trainer")
    private Trainer id_trainer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
