package com.educonnect.raj_narayanan.model;

import static jakarta.persistence.GenerationType.UUID;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = UUID)
    private String paymentid;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double amountPaid;

    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    private String modeOfPayment;

    @OneToOne
    private Admission admission;
    
}
