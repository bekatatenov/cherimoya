package com.cherimoya.cherimoya.dao;

import com.cherimoya.cherimoya.entity.Balance;
import com.cherimoya.cherimoya.enums.BalanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findFirstByUser_idAndStatus(Long User_id, BalanceStatus status);
}
