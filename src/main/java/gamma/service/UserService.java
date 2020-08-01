package gamma.service;

import java.util.*;
import gamma.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAll();

    public void delete(String username);

    public void changeRoleUser(UserServiceModel userServiceModel);

    public boolean existsUser(String username);
}
