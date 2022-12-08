package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.dao.BalanceRepository;
import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import com.cherimoya.cherimoya.enums.UsersStatus;
import com.cherimoya.cherimoya.service.BalanceService;
import com.cherimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/balancePage")
    private String balancePage(){
        return "Balance";
    }

    @GetMapping(value = "/balance")
    public Balance balance(@RequestParam("user") User user) {
        return balanceService.getBalanceByUser(user);
    }

    @GetMapping(value = "/balanceUserId")
    public Balance balance(@RequestParam("userId") Long UserId) {
        return balanceService.getBalanceByUserId(UserId);
    }

    @GetMapping(value = "/donate")
    public String donate() {
        return "donate";
    }

    @PostMapping(value = "/reg-user-balance")
    public String newRequisites(@ModelAttribute User user) {
        Balance balance = new Balance();
        if (user.getUsersStatus() == UsersStatus.BANNED) {
            return "you are blocked";
        }
        balance.setStatus(BalanceStatus.ACTIVE);
        balance.setRequisites(user.getRequisite());
        balance.setUser(user);
        return "success";
    }

    @PostMapping(value = "/reg-userId-repuisites")
    public String newRequisites(@RequestParam("userId") Long id) {
        Balance balance = new Balance();
        User user = userService.getId(id);
        balance.setUser(user);
        if (balanceService.getBalanceByUser(balance.getUser()) != null) {
            return "balance-already-exists";
        }
        balance.setCount(BigDecimal.valueOf(((new BigDecimal(0)).setScale(2)).ROUND_HALF_EVEN));
        balanceService.save(balance);
        return "success";
    }

    @PostMapping(value = "/fill-balance")
    public String fillBalance(@RequestParam("userId") Long user, @RequestParam BigDecimal amount) {
        if (balance(user) == null) {
            return "you don't have balance wallet";
        }
        Balance balance = balance(user);
        balance.setCount(BigDecimal.valueOf(BigDecimal.ROUND_HALF_EVEN));
        balanceService.save(balance);
        return "success";
    }

    @PatchMapping(value = "/withdraw-balance")
    public String withDrawBalance(@RequestParam User user, @RequestParam BigDecimal amount) {
        Balance balance = balance(user);
        if (balance == null) {
            return "you don't have balance wallet";
        }
        if (balance.getCount().compareTo(amount) == -1) return "you can't withdraw more than you have";
        balance.setCount(balance.getCount().subtract(amount));
        balanceService.save(balance);
        return "success";
    }

    @PatchMapping(value = "/block-balance")
    public String blockBalance(@RequestParam("balanceId") Long id) {
        balanceService.blockBalance(id);
        return "success";
    }
}
