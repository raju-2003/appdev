package com.educonnect.raj_narayanan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educonnect.raj_narayanan.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>{
    
}
