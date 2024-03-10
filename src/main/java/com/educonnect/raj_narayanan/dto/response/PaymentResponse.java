package com.educonnect.raj_narayanan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private String paymentid;
    private String status;
    private Double amountPaid;
    private String paymentDate;
    private String modeOfPayment;
    private String admissionid;
    
}
