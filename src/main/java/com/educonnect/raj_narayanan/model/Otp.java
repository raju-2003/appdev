package com.educonnect.raj_narayanan.model;

import static jakarta.persistence.GenerationType.UUID;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otp")
public class Otp {

    @Id
    @GeneratedValue(strategy = UUID)
    private String otpid;

    private String email;

    private String otp;

    private LocalDateTime createdTime;

    private LocalDateTime expiryTime;

    private boolean isExpired;

    private boolean isUsed;

    private LocalDateTime usedTime;

    
    
}
