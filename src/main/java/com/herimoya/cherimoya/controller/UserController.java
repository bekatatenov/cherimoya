package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.entity.User;
import com.herimoya.cherimoya.enums.RoleStatus;
import com.herimoya.cherimoya.enums.UsersStatus;
import com.herimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UserController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

    @RequestMapping(value = "/hello", method = RequestMethod.GET )
    public String hello(){
        return "hello";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }
    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user){
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRoles(String.valueOf(RoleStatus.USER));
        user.setUsersStatus(String.valueOf(UsersStatus.FUNCTION));
        this.userService.save(user);
        return "login";
    }

}
