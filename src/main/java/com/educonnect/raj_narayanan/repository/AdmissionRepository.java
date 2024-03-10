package com.educonnect.raj_narayanan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educonnect.raj_narayanan.model.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, String>{
    
}
