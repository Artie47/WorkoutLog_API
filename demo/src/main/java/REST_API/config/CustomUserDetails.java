package REST_API.config;

import REST_API.model.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails{

    private String login;
    private String password;
    private Collection<?> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
        CustomUserDetails c = new CustomUserDetails();
        c.login = user.getEmail();
        c.password = user.getPassword();
        return c;
    }


    public Collection<?> getAuthorities() {
        return grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return login;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
