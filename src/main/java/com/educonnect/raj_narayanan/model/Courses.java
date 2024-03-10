package com.educonnect.raj_narayanan.model;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = UUID)
    private String courseid;

    @Column(nullable = false)
    private String coursename;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String coursedescription;
    
    @Column(nullable = false)
    private String courseduration;

    @Column(nullable = false)
    private double coursefee;

    @Column(nullable = false)
    private int noofseats;

   //institute
    @ManyToOne(fetch = LAZY)
    private Institute institute;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admissionid", referencedColumnName = "admissionid")
    private Admission admission;
    
    public String getCourseid(){
        return courseid;
    }
}
