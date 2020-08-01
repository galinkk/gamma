package gamma.config;


import gamma.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     private final PasswordEncoder passwordEncoder;
     private final UserDetailsServiceImpl userDetailsService;

 @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
                          UserDetailsServiceImpl userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.
        userDetailsService(userDetailsService).
        passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
        authorizeRequests().
            requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
            antMatchers("/").permitAll().
            antMatchers("/users/login**", "/users/login-error**").permitAll().
            antMatchers("/users/login-error**").permitAll().
            antMatchers("/**").
        authenticated().
        and().
            formLogin().
            loginPage("/users/login").
            loginProcessingUrl("/users/login/authenticate").
            failureForwardUrl("/users/login-error").
            successForwardUrl("/homes").
            and().
            logout().
            logoutUrl("/logout").
            logoutSuccessUrl("/users/login").
            invalidateHttpSession(true).
            deleteCookies("JSESSIONID");

  }
}
