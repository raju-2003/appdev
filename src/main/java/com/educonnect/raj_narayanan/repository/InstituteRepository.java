package com.educonnect.raj_narayanan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educonnect.raj_narayanan.model.Institute;

public interface InstituteRepository extends JpaRepository<Institute, String>{

    @Query("SELECT i FROM Institute i WHERE i.institutename = ?1")
    Optional<Institute> findByInstitutename(String institutename);

    @Query("SELECT i FROM Institute i WHERE i.instituteid = ?1")
    Optional<Institute> findByInstituteId(String instituteid);
    
}
