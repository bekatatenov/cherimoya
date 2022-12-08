package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.RoleStatus;
import com.cherimoya.cherimoya.enums.UsersStatus;
import com.cherimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UserController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error",required = false)String error,
            @RequestParam(value = "logout",required = false)String logout){
        ModelAndView model= new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return model;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET )
    public String hello(){
        return "main";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/moderPage")
    public String moderPage(){
        return "moder";
    }

    @GetMapping("/userPage")
    public String userPage(){
        return "user";
    }

    @GetMapping("/recipientPage")
    public String recipientPage(){
        return "recipient";

    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user){
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRole(RoleStatus.USER);
        user.setUsersStatus(UsersStatus.ACTIVE);
        this.userService.save(user);
        return "login";
    }

    @GetMapping("/delete-users")
    public String delete(){
        return "delete";
    }


    @PostMapping(value = "/delete-users-by-email")
    public String deleteByEmail(@ModelAttribute User user){
        this.userService.update(user.getEmail());
        return "login";
    }
}
