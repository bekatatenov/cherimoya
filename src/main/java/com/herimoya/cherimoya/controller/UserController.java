package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.dao.BalanceRepository;
import com.herimoya.cherimoya.entity.Balance;
import com.herimoya.cherimoya.entity.User;
import com.herimoya.cherimoya.enums.BalanceStatus;
import com.herimoya.cherimoya.service.BalanceService;
import com.herimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
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

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String hello() {
        return "main";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authenticator.getName());
        return "admin";
    }

    @GetMapping("/moderPage")
    public String moderPage() {
        return "moder";
    }

    @GetMapping("/userPage")
    public String userPage() {
        return "user";
    }

    @GetMapping("/recipientPage")
    public String recipientPage() {
        return "recipient";

    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user) {
        this.userService.save(user);
        return "user";
    }


    @GetMapping("/delete-users")
    public String delete() {
        return "delete";
    }


    @PostMapping(value = "/delete-users-by-email")
    public String deleteByEmail(@ModelAttribute User user) {
        this.userService.delete(user.getEmail());
        if(user.getBalance()!=null){
            Balance balance = user.getBalance();
            balance.setStatus(BalanceStatus.DELETED);
            balanceService.save(balance);

        }
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

    @GetMapping("/change-to-admin")
    public String admin() {
        return "getAdmin";
    }

    @PostMapping(value = "/change-to-admin-by-email")
    public String changeToAdmin(@ModelAttribute User user) {
        this.userService.admin(user.getEmail());
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
    }

}
