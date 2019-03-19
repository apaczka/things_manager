package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.model.User;
import pl.coderslab.model.UserRole;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @RequestMapping("/all")
    public String showAllUsers(Model model) {
        List<User> users = new ArrayList<>();
        UserRole role = userService.findRole(USER_ROLE);
        users = userService.showAllUsers(role);
        model.addAttribute("users", users);
        return "user/all";
    }

        @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "user/add";
    }
    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.addWithDefaultRole(user);
        return "user/all";
    }
    @GetMapping("/mainpanel")
    public String showUserMainPanel(){
        return "user/mainpanel";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id){
        userService.removeUser(id);
        return "user/all";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }
    @PostMapping("/edit")
    public String updateUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "user/edit";
        }
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userService.addUser(user);
        return "redirect:user/all";
    }
}
