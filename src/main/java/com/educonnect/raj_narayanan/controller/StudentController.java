package com.educonnect.raj_narayanan.controller;

import static com.educonnect.raj_narayanan.utils.MyConstant.USER;
import static com.educonnect.raj_narayanan.utils.MyConstant.STUDENT_ADD;
import static com.educonnect.raj_narayanan.utils.MyConstant.STUDENT_UPDATE;
import static com.educonnect.raj_narayanan.utils.MyConstant.STUDENT_GET;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educonnect.raj_narayanan.dto.request.StudentRequest;
import com.educonnect.raj_narayanan.dto.response.BasicResponse;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.StudentResponse;
import com.educonnect.raj_narayanan.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import java.util.List;

@RestController
@RequestMapping(USER)
@PreAuthorize("hasRole('USER')")
@Tag(name = "Student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping(STUDENT_ADD)
    @Operation(summary = "Fill profile details")
    public ResponseEntity<BasicResponse<StudentResponse>> addStudent(@RequestBody StudentRequest request) {
        BasicResponse<StudentResponse> response = new BasicResponse<>();
        try {
            StudentResponse studentResponse = studentService.addStudent(request);
            response.setData(List.of(studentResponse));
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Student addition failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PutMapping(STUDENT_UPDATE)
    @Operation(summary = "Update Profile Details")
    public ResponseEntity<CommonResponse> updateStudent(@RequestBody StudentRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            response = studentService.updateStudent(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Student update failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @GetMapping(STUDENT_GET)
    @Operation(summary = "See profile Details")
    public ResponseEntity<BasicResponse<StudentResponse>> getStudent(@RequestParam String email) {
        BasicResponse<StudentResponse> response = new BasicResponse<>();
        try {
            StudentResponse studentResponse = studentService.getStudent(email);
            response.setData(List.of(studentResponse));
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Failed to retrieve student details.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

   

    
}
