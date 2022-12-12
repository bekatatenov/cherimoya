package com.cherimoya.cherimoya.controller;

import com.cherimoya.cherimoya.dao.PaymentRepository;
import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.entity.Payment;
import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.PaymentStatus;
import com.cherimoya.cherimoya.enums.PaymentType;
import com.cherimoya.cherimoya.service.BalanceService;
import com.cherimoya.cherimoya.service.PaymentService;
import com.cherimoya.cherimoya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Controller
public class PaymentController {
    @Autowired
    UserService userService;
    @Autowired
    BalanceService balanceService;
    @Autowired
    private PaymentService paymentService;

//    Balance commisions = balanceService.getBalanceByRequisites("123456789101");
    @Autowired
    private PaymentRepository paymentRepository;

    private BigDecimal commissionPayment(BigDecimal cash) {
        return cash.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(1)).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal minus(Balance balance, BigDecimal cash) {
        return balance.getAmount().subtract(cash).setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean minusCheck(Balance balance, BigDecimal cash) {
        return balance.getAmount().compareTo(cash) >= 0;
    }

    private BigDecimal plus(Balance balance, BigDecimal cash) {
        return balance.getAmount().add(cash).setScale(2, RoundingMode.HALF_EVEN);
    }

//    @PostMapping(value = "/donate")
//    public String donate(@RequestParam String requisites, @RequestParam BigDecimal cash) {
//        User user = userService.auth();
//        Payment payment = new Payment();
//        payment.setDate(new Date());
//        payment.setFromBalance(user.getBalance());
//        payment.setToBalance(balanceService.getBalanceByRequisites(requisites));
//        payment.setType(PaymentType.donate);
//        payment.setStatus(PaymentStatus.success);
//        payment.setAmount(cash.add(commissionPayment(cash)).setScale(2, RoundingMode.HALF_EVEN));
//        Balance fromBalance = payment.getFromBalance();
//        Balance toBalance = payment.getToBalance();
//        if (minusCheck(fromBalance, payment.getAmount())) {
//            fromBalance.setAmount(minus(fromBalance, cash));
//            balanceService.save(fromBalance);
//            toBalance.setAmount(plus(toBalance, cash));
//            balanceService.save(toBalance);
//            commisions.setAmount(commisions.getAmount().add(commissionPayment(cash)).setScale(2, RoundingMode.HALF_EVEN));
//            balanceService.save(commisions);
//        } else {
//            payment.setStatus(PaymentStatus.error);
//            return "error";
//        }
//        paymentService.save(payment);
//        return "success";
//    }

//    @PatchMapping(value = "/cancel")
//    public String cancel(@RequestParam Long id) {
//        try {
//            Payment payment = paymentService.getPaymentByID(id);
//            payment.setStatus(PaymentStatus.canceled);
//            Balance fromBalance = payment.getFromBalance();
//            fromBalance.setAmount(plus(fromBalance, commissionPayment(payment.getAmount())));
//            fromBalance.setAmount(plus(fromBalance, payment.getAmount()));
//            balanceService.save(fromBalance);
//            Balance toBalance = payment.getToBalance();
//            toBalance.setAmount(minus(toBalance, payment.getAmount()));
//            balanceService.save(toBalance);
//            toBalance.setAmount(minus(commisions, commissionPayment(payment.getAmount())));
//            balanceService.save(commisions);
//            paymentService.save(payment);
//            return "success";
//        } catch (Exception e) {
//            return error(id);
//        }
//
//    }

    @PatchMapping(value = "")
    public String error(@RequestParam Long id) {
        Payment payment = paymentService.getPaymentByID(id);
        payment.setStatus(PaymentStatus.error);
        return "error";
    }
}
