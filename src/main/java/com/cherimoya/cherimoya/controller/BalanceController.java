package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.dao.BalanceRepository;
import com.cherimoya.cherimoya.dao.UserRepository;
import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import com.cherimoya.cherimoya.service.BalanceService;
import com.cherimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
@RequestMapping("/balance")
public class BalanceController {

    private User auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }

    private boolean commission(Balance balance, BigDecimal cash) {
        cash.add(cash.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2)).setScale(2, RoundingMode.HALF_EVEN));
        return cash.compareTo(balance.getAmount()) >= 0;
    }

    private BigDecimal commissionWithDraw(BigDecimal cash) {
        return cash.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2)).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal commissionPayment(Balance balance, BigDecimal cash) {
        return cash.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(1)).setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean checkBalance(User user) {
        Balance balance = user.getBalance();
        if (balance.getStatus() == BalanceStatus.BLOCK || balance.getStatus() == BalanceStatus.DELETED) return false;
        if (balance == null) return false;
        return true;
    }

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private BalanceRepository balanceRepository;

    @GetMapping(value = "/balancePage")
    private String balancePage() {
        return "Balance";
    }

    @GetMapping(value = "/balance")
    public Balance balance() {
        return auth().getBalance();
    }

    @GetMapping(value = "/donate")
    public String donate() {
        return "donate";
    }

    @GetMapping(value = "/getBalance")
    public String getBalance() {
        return "Balance";
    }

    @GetMapping(value = "show-Balance")
    public Balance showBalance() {
        User user = auth();
        return user.getBalance();
    }

    @PostMapping(value = "new-balance")
    public String newBalance(@RequestParam String reqisites) {
        User user = auth();
        if (user.getBalance() != null) return "you already have balance";
        if (balanceRepository.findBalanceByRequisites(reqisites) != null) return "requisites already is using";
        Balance balance = new Balance();
        balance.setStatus(BalanceStatus.ACTIVE);
        balance.setAmount(BigDecimal.valueOf(0));
        balance.setRequisites(reqisites);
        balanceService.save(balance);
        return "success";
    }

    @PatchMapping(value = "/fill-balance")
    public String fillBalance(@RequestParam BigDecimal amount) {
        User user = auth();
        if (!checkBalance(user)) {
            return "you don't have balance wallet or it is blocked";
        }
        Balance balance = user.getBalance();
        balance.setAmount((balance.getAmount().add(amount)).setScale(2, RoundingMode.HALF_EVEN));
        balanceService.save(balance);
        return "success";
    }

    @PatchMapping(value = "/withdraw-balance")
    public String withDrawBalance(@RequestParam BigDecimal amount) {
        User user = auth();
        Balance balance = user.getBalance();
        Balance company = balanceRepository.findBalanceByRequisites("123456789101");
        if (!checkBalance(user)) {
            return "you don't have balance wallet or it's blocked";
        }
        if (!commission(balance, amount)) return "not enough to withdraw";
        balance.setAmount(balance.getAmount().subtract(amount.add(commissionWithDraw(amount))));
        company.setAmount(company.getAmount().add(commissionWithDraw(amount)));
        balanceService.save(balance);
        return "success";
    }

    @PatchMapping(value = "/block-balance")
    public String blockBalance(@RequestParam("balanceId") Long id) {
        balanceService.blockBalance(id);
        return "success";
    }

    @PatchMapping(value = "self-block-balance")
    public String selfBlockBalance() {
        User user = auth();
        Balance balance = user.getBalance();
        balance.setStatus(BalanceStatus.BLOCK);
        balanceService.save(balance);
        return "success";
    }
}
