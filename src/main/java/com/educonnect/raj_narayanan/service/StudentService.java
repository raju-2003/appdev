package com.educonnect.raj_narayanan.service;

import com.educonnect.raj_narayanan.dto.request.StudentRequest;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.StudentResponse;

public interface StudentService {

    StudentResponse addStudent(StudentRequest request);

    CommonResponse updateStudent(StudentRequest request);

    StudentResponse getStudent(String email);

   
    
    
}
