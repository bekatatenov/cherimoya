package com.herimoya.cherimoya.service;

import com.herimoya.cherimoya.dao.PaymentRepository;
import com.herimoya.cherimoya.entity.Balance;
import com.herimoya.cherimoya.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public void save(Payment payment) {
        this.paymentRepository.save(payment);
    }

    public Payment getPaymentByID(Long id) {
        return paymentRepository.findById(id).get();
    }

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public List<Payment> getAllPaymentByFromBalance(Balance balance) {
        return paymentRepository.findAllByFromBalance(balance);
    }

    public List<Payment> getAllPaymentByToBalance(Balance balance) {
        return paymentRepository.findAllByToBalance(balance);
    }

    public List<Payment> getAllPaymentByDate(Date date) {
        return paymentRepository.findAllByDate(date);
    }


}