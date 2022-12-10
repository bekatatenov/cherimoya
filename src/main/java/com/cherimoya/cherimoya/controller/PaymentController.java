//package com.cherimoya.cherimoya.controller;
//
//import com.cherimoya.cherimoya.dao.UserRepository;
//import com.cherimoya.cherimoya.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.math.BigDecimal;
//
//@Controller(value = "/payment")
//public class PaymentController {
//    @Autowired
//    UserRepository userRepository;
//
//    private User auth() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return userRepository.findByEmail(authentication.getName());
//    }
//
//    @PostMapping(value = "donate")
//    public String donate(@RequestParam String requisites, @RequestParam BigDecimal cash){
////
//    }
//}
