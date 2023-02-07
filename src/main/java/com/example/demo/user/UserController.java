package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

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
    public String registerProcess(@Valid UserModle user,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            redirectAttributes.addFlashAttribute("error", message);
            return "redirect:/register";
        }
        userService.addUser(user);
        return "redirect:/hello";
    }
}

