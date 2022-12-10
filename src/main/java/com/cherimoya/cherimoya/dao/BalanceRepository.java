package com.cherimoya.cherimoya.dao;

import com.cherimoya.cherimoya.entity.*;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import com.cherimoya.cherimoya.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findBalanceById(Long id);
    Balance findBalanceByRequisites(String requisites);
}
