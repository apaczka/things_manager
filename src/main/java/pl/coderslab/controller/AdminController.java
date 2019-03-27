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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_ROLE = "ROLE_ADMIN";


    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/mainpanel")
    public String showUserMainPanel(){
        return "admin/mainpanel";
    }

    @RequestMapping("/all")
    public String showAllAdmins(Model model) {
        List<User> admins = new ArrayList<>();
        UserRole role = userService.findRole(ADMIN_ROLE);
        admins = userService.showAllUsers(role);
        model.addAttribute("admins", admins);
        return "admin/all";

    }

    @RequestMapping("/remove/{id}")
    public String removeAdmin(@PathVariable Long id) {
        userService.removeUser(id);
        return "redirect:/admin/all";

    }

    @GetMapping("/add")
    public String addAdminForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addAdmin(@Valid User user, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            return "admin/add";
        }
        userService.addWithAdminRole(user);
        return "redirect:/admin/all";

    }

    @GetMapping("edit/{id}")
    public String editAdmin(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        List<UserRole> roles = user.getRoles();
        UserRole role = roles.get(0);
        Long roleId = role.getId();
        model.addAttribute("roleId", roleId);
        model.addAttribute("user", user);
        return "admin/edit";
    }

    @PostMapping("/edit")
    public String updateAdmin(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit";
        }
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userService.addUser(user);
        return "redirect:/admin/all";
    }
}
