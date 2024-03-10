package com.educonnect.raj_narayanan.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educonnect.raj_narayanan.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, String>{

    Optional<Otp> findByEmail(String email);
    
}
