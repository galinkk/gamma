package gamma.model.service;

public class AuthorityEntityServiceModel extends BaseServiceModel{
    private String name;
    private UserServiceModel user;

    public AuthorityEntityServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
