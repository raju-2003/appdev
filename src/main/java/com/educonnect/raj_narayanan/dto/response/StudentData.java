package com.educonnect.raj_narayanan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentData {
    private String studentid;
    private String dob; // Corrected to be of type String
    private String gender;
    private String motherName;
    private String fatherName;
    private String nationality;
    private long age;
    private String address;
    private double marksSSLC;
    private double marksHSC;
}