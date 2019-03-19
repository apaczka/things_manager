package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.model.User;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model. addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String addUser(@Valid User user,
                          BindingResult bindResult) {
        if(bindResult.hasErrors())
            return "register";
        else {
            userService.addWithDefaultRole(user);
            return "login";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }


}
