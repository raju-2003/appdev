package com.educonnect.raj_narayanan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesResponse {
    private String courseid;    
    private String coursename;
    private String coursedescription;
    private String courseduration;
    private double coursefee;
    private int noofseats;
    private String institutename;    
}
