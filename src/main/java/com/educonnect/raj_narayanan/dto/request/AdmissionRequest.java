package com.educonnect.raj_narayanan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRequest {

    private String admissionid;
    private String status;
    private String studentid;
    private String paymentid;
    private String courses;
    
}
