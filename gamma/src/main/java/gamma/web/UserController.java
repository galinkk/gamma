package gamma.web;

import gamma.model.binding.UserAddBindingModel;
import gamma.model.service.AuthorityEntityServiceModel;
import gamma.model.service.UserServiceModel;
import gamma.model.view.UserViewModel;
import gamma.service.AuthorityEntityService;
import gamma.service.RoleService;
import gamma.service.UserService;
import gamma.user.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final UserService userService;

    public UserController(UserDetailsServiceImpl userDetailsService, ModelMapper modelMapper, RoleService roleService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(){
        return "redirect:/login-error";
    }

    @PostMapping("/login-error")
    public ModelAndView onLoginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.
                    SPRING_SECURITY_FORM_USERNAME_KEY) String username
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", username);
        modelAndView.setViewName("login");
     return modelAndView;
    }
}
