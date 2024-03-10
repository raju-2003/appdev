package com.educonnect.raj_narayanan.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String paymentid;
    private String status;
    private Double amountPaid;
    private Date paymentDate;
    private String modeOfPayment;
    private String admissionid;

}
