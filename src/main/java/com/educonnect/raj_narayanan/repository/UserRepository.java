package com.educonnect.raj_narayanan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educonnect.raj_narayanan.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndStudentStudentidIsNull(String email);

    Optional<User> findByEmailAndStudentStudentidIsNotNull(String email);
}