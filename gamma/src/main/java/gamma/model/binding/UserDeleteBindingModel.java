package gamma.model.binding;

import javax.validation.constraints.NotNull;

public class UserDeleteBindingModel {
    private String username;

    public UserDeleteBindingModel() {
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
