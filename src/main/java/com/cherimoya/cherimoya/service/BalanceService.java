package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.BalanceRepository;
import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

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

    public void save(Balance balance) {
        this.balanceRepository.save(balance);
    }
}
