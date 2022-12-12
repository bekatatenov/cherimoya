package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.dao.BalanceRepository;
import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.entity.Payment;
import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import com.cherimoya.cherimoya.enums.PaymentStatus;
import com.cherimoya.cherimoya.enums.PaymentType;
import com.cherimoya.cherimoya.service.BalanceService;
import com.cherimoya.cherimoya.service.PaymentService;
import com.cherimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Controller
public class BalanceController {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BalanceRepository balanceRepository;

    private boolean commission(Balance balance, BigDecimal cash) {
        cash.add(cash.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2)).setScale(2, RoundingMode.HALF_EVEN));
        return cash.compareTo(balance.getAmount()) >= 0;
    }

    private BigDecimal commissionWithDraw(BigDecimal cash) {
        return cash.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2)).setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean checkBalance(User user) {
        Balance balance = user.getBalance();
        if (balance.getStatus() == BalanceStatus.BLOCK || balance.getStatus() == BalanceStatus.DELETED) return false;
        return balance != null;
    }

    @GetMapping(value = "/balancePage")
    private String balancePage() {
        return "balance";
    }

    @GetMapping(value = "/balanceListPage")
    private List<Balance> balanceList(){
        return balanceService.balanceList();
    }

    @GetMapping(value = "/balance")
    public Balance balance() {
        return userService.auth().getBalance();
    }

    @GetMapping(value = "/show-Balance")
    public Balance showBalance() {
        User user = userService.auth();
        return user.getBalance();
    }
//    страница заполнения баланса
    @GetMapping(value = "balance-fill")
    public String balanceFill(){
        return "balanceFill";
    }

//    Создаёт новый баланс
    @PostMapping(value = "/new-balance")
    public String newBalance(@RequestParam String requisites) {
        User user = userService.auth();
        if (user.getBalance() != null) return "error";
        if (balanceRepository.findBalanceByRequisites(requisites) != null) return "requisites already is using";
        Balance balance = new Balance();
        balance.setStatus(BalanceStatus.ACTIVE);
        balance.setAmount(BigDecimal.valueOf(0));
        balance.setRequisites(requisites);
        balanceService.save(balance);
        user.setBalance(balance);
        userService.save(user);
        System.out.println("success");
        return "user";
    }
//    заполняет баланс
    @PostMapping(value = "/fill-balance")
    public String fillBalance(@RequestParam String amount1) {
        BigDecimal amount = new BigDecimal(amount1);
        User user = userService.auth();
        if (checkBalance(user)==false) {
            return "error";
        }
        Balance balance = user.getBalance();
        balance.setAmount((balance.getAmount().add(amount)).setScale(2, RoundingMode.HALF_EVEN));
        balanceService.save(balance);
        Payment payment = new Payment();
        payment.setAmount(amount.add(commissionWithDraw(amount)));
        payment.setDate(new Date());
        payment.setType(PaymentType.fillBalance);
        payment.setFromBalance(balance);
        payment.setStatus(PaymentStatus.success);
        paymentService.save(payment);
        return "success";
    }
//снимает деньги
    @PatchMapping(value = "/withdraw-balance")
    public String withDrawBalance(@RequestParam BigDecimal amount) {
        User user = userService.auth();
        Balance balance = user.getBalance();
        Balance company = balanceRepository.findBalanceByRequisites("123456789101");
        if (!checkBalance(user)) {
            return "you don't have balance wallet or it's blocked";
        }
        if (!commission(balance, amount)) return "not enough to withdraw";
        balance.setAmount(balance.getAmount().subtract(amount.add(commissionWithDraw(amount))));
        company.setAmount(company.getAmount().add(commissionWithDraw(amount)));
        balanceService.save(balance);
        Payment payment = new Payment();
        payment.setAmount(amount.add(commissionWithDraw(amount)));
        payment.setDate(new Date());
        payment.setType(PaymentType.withdrawMoney);
        payment.setFromBalance(balance);
        payment.setStatus(PaymentStatus.success);
        paymentService.save(payment);
        return "success";
    }

//    блокирует баланс
    @PatchMapping(value = "/block-balance-by-id")
    public String blockBalance(@RequestParam("balanceId") Long id) {
        balanceService.blockBalance(id);
        return "success";
    }

//    блокирует баланс по реквизитам
    @PatchMapping(value = "/block-balance-requisites")
    public String blockBalanceByRequisites(@RequestParam("requisites") String requisites) {
        balanceService.blockBalanceByRequisites(requisites);
        return "success";
    }

//    блокирует баланс по email
    @PatchMapping(value = "/block-balance-by-email")
    public String blockBalance(@RequestParam("UserEmail") String email) {
        balanceService.blockBalance(email);
        return "success";
    }

//    блокирует баланс по id
    @PatchMapping(value = "/activate-balance-by-id")
    public String activateBalance(@RequestParam("balanceId") Long id) {
        balanceService.blockBalance(id);
        return "success";
    }

//    активирует баланс
    @PatchMapping(value = "/activate-balance-requisites")
    public String activateBalanceByRequisites(@RequestParam("requisites") String requisites) {
        balanceService.blockBalanceByRequisites(requisites);
        return "success";
    }

//    активирует баланс по email
    @PatchMapping(value = "/activate-balance-by-email")
    public String activateBalance(@RequestParam("UserEmail") String email) {
        balanceService.blockBalance(email);
        return "success";
    }

//    блокирует свой баланс
    @PatchMapping(value = "/self-block-balance")
    public String selfBlockBalance() {
        User user = userService.auth();
        Balance balance = user.getBalance();
        balance.setStatus(BalanceStatus.BLOCK);
        balanceService.save(balance);
        return "success";
    }

//    блокирует баланс по реквизитам
    @GetMapping(value = "/get-balance-by-requisites")
    public Balance getBalanceByRequisites(@RequestParam String requisites) {
        return balanceService.getBalanceByRequisites(requisites);
    }
}
