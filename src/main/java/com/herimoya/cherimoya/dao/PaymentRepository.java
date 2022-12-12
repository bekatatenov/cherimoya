package com.herimoya.cherimoya.dao;

import com.herimoya.cherimoya.entity.Balance;
import com.herimoya.cherimoya.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByDate(Date date);

    Payment findByToBalance(Balance balance);

    Payment findByFromBalance(Balance balance);

    Optional<Payment> findById(Long id);

    List<Payment> findAllByDate(Date date);

    List<Payment> findAllByFromBalance(Balance balance);

    List<Payment> findAllByToBalance(Balance balance);


}