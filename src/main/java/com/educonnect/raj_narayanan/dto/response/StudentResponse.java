package com.educonnect.raj_narayanan.dto.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private String message;
    private List<StudentData> data;
}
