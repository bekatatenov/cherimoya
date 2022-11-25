package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping(value = "balace")
    public String balance(){
        return "balance";
    }

    @PostMapping(value = "check_balance")
    public String check_balance(@ModelAttribute Balance balance){
        balance.setCount();
        return balance;
    }

}
