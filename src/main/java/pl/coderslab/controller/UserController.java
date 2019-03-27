package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    private static final String USER_ROLE = "ROLE_USER";

    private void setUser(Principal principal) {
        if (principal == null) {
            session.removeAttribute("user");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getEmail().equals(principal.getName())) {
            String email = principal.getName();
            user = userRepository.findByEmail(email);
            session.setAttribute("user", user);
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/all")
    public String showAllUsers(Model model) {
        List<User> users = new ArrayList<>();
        UserRole role = userService.findRole(USER_ROLE);
        users = userService.showAllUsers(role);
        model.addAttribute("users", users);
        return "user/all";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.addWithDefaultRole(user);
        return "redirect:/user/all";
    }

    @GetMapping("/mainpanel")
    public String showUserMainPanel(Principal principal) {
        setUser(principal);
        return "user/mainpanel";
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/user/all";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        List<UserRole> roles = user.getRoles();
        UserRole role = roles.get(0);
        Long roleId = role.getId();
        model.addAttribute("roleId", roleId);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String updateUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit";
        }
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userService.addUser(user);
        if (user.getRoles().equals("ROLE_ADMIN")){
            return "redirect:/user/all";
    }else{
        return "redirect:/user/mainpanel";
    }
}
}
