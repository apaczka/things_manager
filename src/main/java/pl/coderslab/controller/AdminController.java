package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.model.User;
import pl.coderslab.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Autowired
    UserService userService;

    @RequestMapping("/all")
    public String showAllAdmins(Model model){
        List<User> admins = new ArrayList<>();
        admins=userService.showAllUsers(ADMIN_ROLE);
        model.addAttribute("admins", admins);
        return "admins";

    }
}
