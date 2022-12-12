package com.herimoya.cherimoya.controller;


import com.herimoya.cherimoya.dao.UserRepository;
import com.herimoya.cherimoya.entity.User;
import com.herimoya.cherimoya.enums.RoleStatus;
import com.herimoya.cherimoya.enums.UsersStatus;
import com.herimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@Controller
public class UserController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/settings")
    public String userEdit(@PathVariable(value = "id") Long id, Model model) {
        if (userRepository.existsById(id)) {
            return "redirect:/profile";
        }
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "profile-settings";
    }

    @PostMapping(value = "/settings")
    public String userProfileEdit(@PathVariable(value = "id") Long id, @RequestParam String name, String email, String password,
                                  String phoneNumber, String requisite, String profilePhoto, Model model) {
        User user = userRepository.findById(id).orElseThrow(null);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setRequisite(requisite);
        user.setProfilePhoto(profilePhoto);
        user.setUsersStatus(UsersStatus.ACTIVE);
        user.setDate(new Date());
        userRepository.save(user);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error",required = false)String error,
                                  @RequestParam(value = "sigin",required = false)String sigin,
                                  @RequestParam(value = "logout",required = false)String logout){
        ModelAndView model= new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("login");
        }
            if (sigin == null) {
                model.addObject("sigin", "Sig in out successfully.");
                model.setViewName("home-main");
            }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("login");
        }
        return model;
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user) {
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRoles(RoleStatus.USER);
        user.setUsersStatus(UsersStatus.ACTIVE);
        this.userService.save(user);
        return "login";
    }

    @GetMapping("/delete-users")
    public String delete(Model model) {
        return "/deleteUser";
    }

    @PostMapping(value = "/delete-users-by-email")
    public String deleteByEmail(@ModelAttribute User user) {
        this.userService.delete(user.getEmail());
        return "login";
    }

    @GetMapping("/ban-users")
    public String ban() {
        return "ban";
    }

    @PostMapping(value = "/ban-users-by-email")
    public String banByEmail(@ModelAttribute User user) {
        this.userService.ban(user.getEmail());
        return "login";
    }

    @GetMapping("/active-users")
    public String active() {
        return "active";
    }

    @PostMapping(value = "/active-users-by-email")
    public String activeByEmail(@ModelAttribute User user) {
        this.userService.active(user.getEmail());
        return "login";
    }

    @GetMapping("/change-to-moder")
    public String moder() {
        return "getModer";
    }

    @PostMapping(value = "/change-to-moder-by-email")
    public String changeToModer(@ModelAttribute User user) {
        this.userService.moder(user.getEmail());
        return "login";
    }

    @GetMapping("/change-to-user")
    public String user() {
        return "getUser";
    }

    @PostMapping(value = "/change-to-user-by-email")
    public String changeToUser(@ModelAttribute User user) {
        this.userService.user(user.getEmail());
        return "login";
    }

    @GetMapping("/change-to-recipient")
    public String recipient() {
        return "getRecipient";
    }

    @PostMapping(value = "/change-to-recipient-by-email")
    public String changeToRecipient(@ModelAttribute User user) {
        this.userService.recipient(user.getEmail());
        return "login";
    }/*
*/
    @GetMapping("/change-to-admin")
    public String admin() {
        return "getAdmin";
    }

    @PostMapping(value = "/change-to-admin-by-email")
    public String changeToAdmin(@ModelAttribute User user) {
        this.userService.admin(user.getEmail());
        return "login";
    }
    @GetMapping("/delete")
    public String delete(){
        return "delete_users";
    }


}