package com.herimoya.cherimoya.controller;

import com.herimoya.cherimoya.dao.UserRepository;
import com.herimoya.cherimoya.entity.NewPasswordUser;
import com.herimoya.cherimoya.entity.Token;
import com.herimoya.cherimoya.entity.User;
import com.herimoya.cherimoya.service.EmailSendlerService;
import com.herimoya.cherimoya.service.TokenService;
import com.herimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRepository userRepository;

    @Autowired
    private EmailSendlerService emailSendlerService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    private

    @GetMapping(value = "/forgotPassword")
    String resetPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping(value = "/passwordRecoveryEmail")
    public ModelAndView getEmailForResetPassword(@RequestParam String login) throws MessagingException {
        ModelAndView modelAndView = new ModelAndView("changePassword");
        User saved = userService.FindByLogin(login);
        Token token = tokenService.saveToken(saved, tokenService.makeToken());
        emailSendlerService.sendEmail(saved.getEmail(), "Введите данный токен, чтобы сбросить ваш пароль: " + String.valueOf(token.getToken()), "Восстановление пароля");
        NewPasswordUser newPasswordUser = new NewPasswordUser();
        newPasswordUser.setUserEmail(saved.getEmail());
        modelAndView.addObject("reset", newPasswordUser);
        return modelAndView;
    }

    @PostMapping(value = "/newPasswordUser")
    public String newPassword(@ModelAttribute(name = "reset") NewPasswordUser newPasswordUser) throws Exception {
        User user = userRepository.findFirstByEmail(newPasswordUser.getUserEmail());
        Token byUserAndToken = tokenService.findByUserAndToken(user, newPasswordUser.getToken());
        if (newPasswordUser.getPassword().equals(newPasswordUser.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPasswordUser.getPassword()));
            userService.update(user);
            tokenService.deleteToken(byUserAndToken);
            return "login";
        } else {
            return "changePassword";
        }
    }
}