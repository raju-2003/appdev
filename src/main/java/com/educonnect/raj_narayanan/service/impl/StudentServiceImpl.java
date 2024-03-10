package com.educonnect.raj_narayanan.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.educonnect.raj_narayanan.dto.request.StudentRequest;
import com.educonnect.raj_narayanan.dto.response.CommonResponse;
import com.educonnect.raj_narayanan.dto.response.StudentData;
import com.educonnect.raj_narayanan.dto.response.StudentResponse;
import com.educonnect.raj_narayanan.model.Student;
import com.educonnect.raj_narayanan.model.User;
import com.educonnect.raj_narayanan.repository.StudentRepository;
import com.educonnect.raj_narayanan.repository.UserRepository;
import com.educonnect.raj_narayanan.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public StudentResponse addStudent(StudentRequest request) {
        Optional<User> optionalUser = userRepository.findByEmailAndStudentStudentidIsNull(request.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Create the student entity
            Student student = Student.builder()
                    .dob(request.getDob())
                    .gender(request.getGender())
                    .motherName(request.getMotherName())
                    .fatherName(request.getFatherName())
                    .nationality(request.getNationality())
                    .age(request.getAge())
                    .address(request.getAddress())
                    .marksSSLC(request.getMarksSSLC())
                    .marksHSC(request.getMarksHSC())
                    .build();

            // Save the student to get the studentid
            student = studentRepository.save(student);

            // Update the user with the generated studentid
            user.setStudent(student);
            userRepository.save(user);

            // Construct the response
            StudentData studentData = StudentData.builder()
                    .studentid(student.getStudentid())
                    .dob(student.getDob())
                    .gender(student.getGender())
                    .motherName(student.getMotherName())
                    .fatherName(student.getFatherName())
                    .nationality(student.getNationality())
                    .age(student.getAge())
                    .address(student.getAddress())
                    .marksSSLC(student.getMarksSSLC())
                    .marksHSC(student.getMarksHSC())
                    .build();

            // Prepare and return the response
            return StudentResponse.builder()
                    .message("Student added successfully")
                    .data(Collections.singletonList(studentData))
                    .build();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public StudentResponse getStudent(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        Optional<Student> optionalStudent = studentRepository.findById(optionalUser.get().getStudent().getStudentid());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            // Construct the response
            StudentData studentData = StudentData.builder()
                    .studentid(student.getStudentid())
                    .dob(student.getDob())
                    .gender(student.getGender())
                    .motherName(student.getMotherName())
                    .fatherName(student.getFatherName())
                    .nationality(student.getNationality())
                    .age(student.getAge())
                    .address(student.getAddress())
                    .marksSSLC(student.getMarksSSLC())
                    .marksHSC(student.getMarksHSC())
                    .build();

            // Prepare and return the response
            return StudentResponse.builder()
                    .message("Student retrieved successfully")
                    .data(Collections.singletonList(studentData))
                    .build();
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    @Override
    public CommonResponse updateStudent(StudentRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        Optional<Student> optionalStudent = studentRepository.findById(optionalUser.get().getStudent().getStudentid());
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setDob(request.getDob());
            student.setGender(request.getGender());
            student.setMotherName(request.getMotherName());
            student.setFatherName(request.getFatherName());
            student.setNationality(request.getNationality());
            student.setAge(request.getAge());
            student.setAddress(request.getAddress());
            student.setMarksSSLC(request.getMarksSSLC());
            student.setMarksHSC(request.getMarksHSC());
            studentRepository.save(student);
            return CommonResponse.builder()
                    .message("Student updated successfully")
                    .build();
        } else {
            throw new RuntimeException("Student not found");
        }
        
    }

    

}
