package com.herimoya.cherimoya.controller;


import com.Project.Post2.dao.UserRepository;
import com.Project.Post2.entity.User;
import com.Project.Post2.enums.RoleStatus;
import com.Project.Post2.enums.UsersStatus;
import com.Project.Post2.service.UserService;
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


    @GetMapping(name ="/profile")
    public String ProfilePage(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("Ты не авторизовался");
        }
        User user = userService.findByName(principal.getName());

        User user1 = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
        model.addAttribute("users", user1);
        return "profile";
    }

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
        User user = userRepository.findById(id).orElseThrow();
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
                                  @RequestParam(value = "logout",required = false)String logout){
        ModelAndView model= new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("login");
        }
        return model;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user) {
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRecipent(false);
        user.setRoles(RoleStatus.USER);
        user.setUsersStatus(UsersStatus.ACTIVE);
        this.userService.save(user);
        return "loginNew";
    }


}