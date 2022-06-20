package REST_API.model;


import lombok.*;

import java.util.Date;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String hashPassword;
    private Date date_of_reg;
    private int id_group;

    @Transient
    private String password;


}