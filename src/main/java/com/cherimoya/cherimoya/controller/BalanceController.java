package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.entity.*;
import com.cherimoya.cherimoya.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping(value = "balace")
    public Balance balance(User user){
        return balanceService.getBalanceByUser(user);
    }

    @PutMapping(value = "donate")
    public String donate(){
        return "donate";
    }

}
