package com.herimoya.cherimoya.controller;

import com.Project.Post2.entity.Post;
import com.Project.Post2.entity.User;
import com.Project.Post2.service.PostService;
import com.Project.Post2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @GetMapping(value = "/")
    public String home(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User byEmail = userService.findByEmail(username);
        List<Post> posts = postService.findAllActive();
        model.addAttribute("title", "Главная страница");
        model.addAttribute("posts", posts);
        model.addAttribute("user", byEmail);
        return "home";
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
