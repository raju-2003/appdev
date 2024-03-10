package com.educonnect.raj_narayanan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.educonnect.raj_narayanan.model.Courses;
import com.educonnect.raj_narayanan.model.Institute;

public interface CoursesRepository extends JpaRepository<Courses, String>{

 
    @Query("SELECT c FROM Courses c WHERE c.coursename = :coursename")
    List<Courses> findByCoursename(String coursename);

    @Query("SELECT c FROM Courses c WHERE c.institute = :institute")
    List<Courses> findByInstitute(Institute institute);
    
}
