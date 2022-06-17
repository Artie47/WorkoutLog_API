package REST_API.DTO;

import java.util.Date;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Date date_of_reg;

    public UserDTO(String name, String email, String password, Date date_of_reg) {
        this.name = name;
        this.email =email;
        this.password = password;
        this.date_of_reg = date_of_reg;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }
}
