package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.BalanceRepository;
import com.cherimoya.cherimoya.dao.UserRepository;
import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.entity.User;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private UserRepository userRepository;

    public void blockBalance(Balance balance) {
        if (balance != null) {
            balance.setStatus(BalanceStatus.BLOCK);
            this.balanceRepository.save(balance);
        }
    }

    public void blockBalance(Long id) {
        Balance balance = this.balanceRepository.findBalanceById(id);
        if (balance != null) {
            balance.setStatus(BalanceStatus.BLOCK);
            this.balanceRepository.save(balance);
        }
    }

    public void blockBalance(String email) {
        User user = userRepository.findByEmail(email);
        Balance balance = user.getBalance();
        if (balance != null) {
            balance.setStatus(BalanceStatus.BLOCK);
            this.balanceRepository.save(balance);
        }
    }

    public void activateBalance(Long id) {
        Balance balance = this.balanceRepository.findBalanceById(id);
        if (balance != null) {
            balance.setStatus(BalanceStatus.ACTIVE);
            this.balanceRepository.save(balance);
        }
    }

    public void activateBalance(String email) {
        User user = userRepository.findByEmail(email);
        Balance balance = user.getBalance();
        if (balance != null) {
            balance.setStatus(BalanceStatus.ACTIVE);
            this.balanceRepository.save(balance);
        }
    }

    public void blockBalanceByRequisites(String requisites) {
        Balance balance = this.balanceRepository.findBalanceByRequisites(requisites);
        if (balance != null) {
            balance.setStatus(BalanceStatus.BLOCK);
            this.balanceRepository.save(balance);
        }
    }

    public Balance getBalanceByRequisites(@RequestParam String requisites) {
        return balanceRepository.findBalanceByRequisites(requisites);
    }

    public List<Balance> balanceList(){
        return balanceRepository.findAll();
    }

    public void save(Balance balance) {
        this.balanceRepository.save(balance);
    }
}
