package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.service.PostService;
import com.herimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @GetMapping(value = "/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/userPage")
    public String userPage() {
        return "userProfile";
    }
    @GetMapping("/moderPage")
    public String moderPage() {
        return "moder";
    }
    @GetMapping("/adminPage")
    public String adminPage() {
        return "admin";
    }

    @GetMapping(value = "/organization")
    public String organization(Model model) {
        model.addAttribute("title", "Организации");
        return "organization";
    }

    @GetMapping(value = "/news")
    public String faq(Model model) {
        model.addAttribute("title", "Информационная страница");
        return "NEWS.kg";
    }

}
