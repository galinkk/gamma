package gamma.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String username;
    private String password;
    private String email;
    private List<AuthorityEntity> authorities;
    private boolean enabled;

    public User() {
    }

    @Column(name = "username",nullable = false,unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @Column(name = "password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    @Column
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
