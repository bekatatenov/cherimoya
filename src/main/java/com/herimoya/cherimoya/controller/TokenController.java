package com.herimoya.cherimoya.controller;

import com.Project.Post2.dto.NewPassUserDto;
import com.Project.Post2.entity.Token;
import com.Project.Post2.entity.User;
import com.Project.Post2.service.EmailSendlerService;
import com.Project.Post2.service.TokenService;
import com.Project.Post2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSendlerService emailSendlerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private

    @GetMapping(value = "/forgotPassword")
    String resetPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping(value = "/passwordRecoveryEmail")
    public ModelAndView getEmailForResetPassword(@RequestParam (name = "email") String mail) throws MessagingException {
        ModelAndView modelAndView = new ModelAndView("newPasswordUser");
        User saved = userService.findByEmail(mail);
        Token token = tokenService.saveToken(saved, tokenService.makeToken());
        emailSendlerService.sendEmail(saved.getEmail(),"Восстановление пароля", "Введите данный токен, чтобы сбросить ваш пароль: " + String.valueOf(token.getToken()));
        NewPassUserDto newPassUser = new NewPassUserDto();
        newPassUser.setEmail(saved.getEmail());
        modelAndView.addObject("reset", newPassUser);
        return modelAndView;
    }

    @PostMapping(value = "/newPasswordUser")
    public String newPassword(@ModelAttribute(name = "reset") NewPassUserDto newPasswordUser) throws Exception {
        User user = userService.findByEmail(newPasswordUser.getEmail());
        Token byUserAndToken = tokenService.findByUserAndToken(user, newPasswordUser.getToken());
        if (newPasswordUser.getPassword().equals(newPasswordUser.getRepeatPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPasswordUser.getPassword()));
            userService.update(user);
            tokenService.deleteToken(byUserAndToken);
            return "login";
        } else {
            return "changePassword";
        }
    }

}