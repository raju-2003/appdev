package com.educonnect.raj_narayanan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstituteRequest {

    private String institutename;
    private String institutedescription;
    private String instituteaddress;
    private long institutephone;
    private String instituteemail;
    private String institutewebsite;
    private long noofcoursesavailale;
    
}
