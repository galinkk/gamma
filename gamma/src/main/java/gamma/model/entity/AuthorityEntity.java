package gamma.model.entity;

import javax.persistence.*;

@Entity
@Table(name="authority")
public class AuthorityEntity extends BaseEntity{
  private String name;
  private User user;

  public AuthorityEntity() {
  }

  public AuthorityEntity(String name) {
    this.name=name;
  }

  @Column(nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
