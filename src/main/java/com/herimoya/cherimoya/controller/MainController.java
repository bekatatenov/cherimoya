package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.dao.SupportRepository;
import com.herimoya.cherimoya.dao.UserRepository;
import com.herimoya.cherimoya.entity.Post;
import com.herimoya.cherimoya.entity.Support;
import com.herimoya.cherimoya.entity.User;
import com.herimoya.cherimoya.enums.Status;
import com.herimoya.cherimoya.service.PostService;
import com.herimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    SupportRepository supportRepository;


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
    @GetMapping(value = "/technical/support")
    public String technicalMain(Model model) {
        model.addAttribute("title", "Техническая поддержка");
        return "technical_support";
    }

    @PostMapping(value = "/SupportMessage/add-save")
    public String technicalSupportAdd(@RequestParam String name, String email,
                                String phone, String message, Model model) {
        Support support = new Support(name, email, phone, message);
        support.setDate(String.valueOf(new Date()));
        supportRepository.save(support);
        return "redirect:home-main";
    }
}
