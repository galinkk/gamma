package gamma.web;

import gamma.model.binding.UserAddBindingModel;
import gamma.model.binding.UserDeleteBindingModel;
import gamma.model.service.UserServiceModel;
import gamma.model.view.UserViewModel;
import gamma.service.RoleService;
import gamma.service.UserService;
import gamma.user.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class AdminActionController {

    private final UserDetailsServiceImpl userDetailsService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final UserService userService;

    public AdminActionController(UserDetailsServiceImpl userDetailsService, ModelMapper modelMapper, RoleService roleService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                 BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("userAddBindingModel",userAddBindingModel);
        mav.addObject("roles", this.roleService.allRoles());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ModelAndView registerPost(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
    BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/register");
        }else {
            if (userService.existsUser(userAddBindingModel.getUsername())) {
                bindingResult.rejectValue("username",
                        "errors.username",
                        "An account with this username already exists.");
                mav.setViewName("register");
                redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
                mav.addObject("roles", this.roleService.allRoles());
                redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userAddBindingModel",
                        bindingResult);
            } else {
                UserServiceModel userServiceModel =
                        this.userService.registerUser(this.modelMapper.map(userAddBindingModel, UserServiceModel.class));
                mav.setViewName("redirect:/list-users");
            }
        }
        return mav;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list-users")
    public ModelAndView listOfUsers (@AuthenticationPrincipal Principal principal){
        ModelAndView mav= new ModelAndView("list-user");
       List<UserViewModel> userViewModels = this.userService.findAll().stream().
               map(userServiceModel -> modelMapper.map(userServiceModel,UserViewModel.class) )
               .collect(Collectors.toList());
        mav.addObject("users", userViewModels);
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/change")
    public ModelAndView change(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                               BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("change-user");
        mav.addObject("userAddBindingModel",userAddBindingModel);
        mav.addObject("roles", this.roleService.allRoles());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/change")
    public ModelAndView changeRolePost(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                       BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/change");
        }else {
            if (!userService.existsUser(userAddBindingModel.getUsername())) {
                bindingResult.rejectValue("username",
                        "errors.username",
                        "There isn`t user with this username.");
                mav.setViewName("change-user");
                redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
                mav.addObject("roles", this.roleService.allRoles());
                redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userAddBindingModel",
                        bindingResult);
            } else {
                this.userService.changeRoleUser(this.modelMapper.map(userAddBindingModel, UserServiceModel.class));
                mav.setViewName("redirect:/list-users");
            }
        }
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public ModelAndView delete(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                         BindingResult bindingResult, ModelAndView mav, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userAddBindingModel",
                    bindingResult);
            mav.setViewName("redirect:/change");
        }else {
            if (!userService.existsUser(userAddBindingModel.getUsername())) {
                bindingResult.rejectValue("username",
                        "errors.username",
                        "There isn`t user with this username.");
                mav.setViewName("change-user");
                redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
                mav.addObject("roles", this.roleService.allRoles());
                redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "userAddBindingModel",
                        bindingResult);
            } else {
                userService.delete(userAddBindingModel.getUsername());
                mav.setViewName("redirect:/list-users");
            }
        }
        return mav;
    }
}
