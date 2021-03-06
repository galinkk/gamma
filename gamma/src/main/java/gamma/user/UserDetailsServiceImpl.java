package gamma.user;


import gamma.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Optional<gamma.model.entity.User> userOpt = userRepository.findByUsername(username);
    LOGGER.debug("Trying to load user {}. Successful? {}",
            username, userOpt.isPresent());
    return userOpt.
        map(this::map).
        orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
  }

  private User map(gamma.model.entity.User user) {
    List<GrantedAuthority> authorities = user.getAuthorities().
            stream().
            map(r -> new SimpleGrantedAuthority(r.getName())).
            collect(Collectors.toList());
    User result = new User(
            user.getUsername(),
            user.getPassword() != null ? user.getPassword() : "",
            authorities);
    if (user.getPassword() == null) {
      result.eraseCredentials();
    }
    return result;
  }
}
