package com.educonnect.raj_narayanan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educonnect.raj_narayanan.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String>{
    
}
