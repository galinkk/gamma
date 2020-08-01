package gamma.model.binding;

import org.hibernate.validator.constraints.Length;
import java.util.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserAddBindingModel {
    private String username;
    private String password;
    private String email;
    private List<String> authorities;

    public UserAddBindingModel() {
    }

    @Length(min = 2 , max = 10,message = "Username must be between 2 and 10 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2 , max = 10,message = "Password must be between 2 and 10 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
