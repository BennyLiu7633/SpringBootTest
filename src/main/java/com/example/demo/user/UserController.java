package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String viewRegisterPage(Model model) {
        model.addAttribute("name", "註冊");
        model.addAttribute("user", new UserModle());
        return "register";
    }
    @PostMapping("/register")
    public String registerProcess(UserModle user) {
        userService.addUser(user);
        return "redirect:/hello";
    }
}

