package com.herimoya.cherimoya.dao;

import com.herimoya.cherimoya.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findBalanceById(Long id);

    Balance findBalanceByRequisites(String requisites);
}