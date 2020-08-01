package gamma.service.impl;

import gamma.Repository.AuthorityEntityRepository;
import gamma.Repository.UserRepository;
import gamma.model.entity.AuthorityEntity;
import gamma.model.entity.User;
import gamma.model.service.UserServiceModel;
import gamma.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AuthorityEntityRepository authorityEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, AuthorityEntityRepository authorityEntityRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.authorityEntityRepository = authorityEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User newUser = new User();
        newUser.setUsername(userServiceModel.getUsername());
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        newUser.setEnabled(true);
        newUser.setEmail(userServiceModel.getEmail());
        AuthorityEntity authorityEntity = new AuthorityEntity();
        List<AuthorityEntity> roles = new LinkedList<>();
        for (int i = 0; i < userServiceModel.getAuthorities().size(); i++) {
            authorityEntity.setName(userServiceModel.getAuthorities().get(i));
            roles.add(authorityEntity);
        }
        authorityEntity.setUser(newUser);
        newUser.setAuthorities(roles);
        userRepository.saveAndFlush(newUser);
        return userServiceModel;
    }

    @Override
    public List<UserServiceModel> findAll() {
        List<User> allUsersEntity = userRepository.findAll();
        List<UserServiceModel> allUsers = new ArrayList<>();
        for (int i = 0; i < allUsersEntity.size(); i++) {
            User user = allUsersEntity.get(i);
            UserServiceModel map = modelMapper.map(user, UserServiceModel.class);
            List<String> listAuthorityUser = new LinkedList<>();
            for (AuthorityEntity authority : user.getAuthorities()) {
               listAuthorityUser.add(authority.getName());
            }
                map.setAuthorities(listAuthorityUser);
            allUsers.add(map);
        }
      return allUsers;
    }

    @Override
    @Transactional
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    @Transactional
    public void changeRoleUser(UserServiceModel userServiceModel) {
        Optional<User> byUsername = this.userRepository.findByUsername(userServiceModel.getUsername());
        if (byUsername != null) {
            User user = byUsername.orElse(null);
            AuthorityEntity authorityEntity = new AuthorityEntity();
            List<AuthorityEntity> roles = new LinkedList<>();
            for (int i = 0; i < userServiceModel.getAuthorities().size(); i++) {
                authorityEntity.setName(userServiceModel.getAuthorities().get(i));
                roles.add(authorityEntity);
            }
            List<AuthorityEntity> allByUser = this.authorityEntityRepository.findAllByUser(user);
            for (AuthorityEntity entity : allByUser) {
                this.authorityEntityRepository.delete(entity);
            }
            authorityEntity.setUser(user);
            user.setAuthorities(roles);
            userRepository.saveAndFlush(user);
       }
    }

    @Override
    public boolean existsUser(String username) {
        Objects.requireNonNull(username);
        return userRepository.findByUsername(username).isPresent();
    }

}
