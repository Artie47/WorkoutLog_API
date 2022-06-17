package REST_API.model;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User{
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

    @Column(name = "date_of_reg")
    private Date date_of_reg;

    @Column(name = "group_id")
    private int id_group;

    public User(){}

    public User(String name, String email, String password, Date date_of_reg, String id_group){
        this.name = name;
        this.email = email;
        this.password = password;
        this.date_of_reg = date_of_reg;
        this.id_group = Integer.getInteger(id_group);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }

    public int getGroup(){
        return  id_group;
    }

    public void setGroup(int group){
        this.id_group = group;
    }

    @Override
    public String toString(){
        return "User:" + "\n" + "ID:" + id + "\n" + "Email:" + email + "\n" + "Password" + password
                + "\n" + "Date of registration" + date_of_reg + "\n" + "Group: " + id_group;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (!this.getClass().equals(object.getClass())) return false;

        User object2 = (User) object;
        if ((this.id == object2.getId()) && (this.email == object2.getEmail()) &&
                (this.password == object2.getPassword()) && (this.id_group == object2.getGroup()) &&
                (this.date_of_reg == object2.getDate_of_reg())
        ) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = email == null ? 0 : email.hashCode();
        result = (29 * result + id);
        result = (29 * result + password).hashCode();
        return result;
    }

}