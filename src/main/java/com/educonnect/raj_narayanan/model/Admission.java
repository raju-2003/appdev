package com.educonnect.raj_narayanan.model;

import static jakarta.persistence.GenerationType.UUID;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = UUID)
    private String admissionid;

    @OneToMany(mappedBy = "admission")
    private List<Courses> courses;

    @Column(nullable = false)
    private String status;

    @OneToOne(mappedBy = "admission")
    private Student student;

    @OneToOne(mappedBy = "admission")
    private Payment payment;

    
    
}
