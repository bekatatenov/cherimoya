package com.cherimoya.cherimoya.service;

import com.cherimoya.cherimoya.dao.BalanceRepository;
import com.cherimoya.cherimoya.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    public void save(Balance balance) {
        this.balanceRepository.save(balance);
    }
}
