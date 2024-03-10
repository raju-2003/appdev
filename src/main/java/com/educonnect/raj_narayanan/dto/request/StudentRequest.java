package com.educonnect.raj_narayanan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String email;
    private String dob;
    private String gender;
    private String motherName;
    private String fatherName;
    private String nationality;
    private Long age;
    private String address;
    private double marksSSLC;
    private double marksHSC;
}
