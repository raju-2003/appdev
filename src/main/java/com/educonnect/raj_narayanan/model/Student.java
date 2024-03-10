package com.educonnect.raj_narayanan.model;

import static jakarta.persistence.GenerationType.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = UUID)
    private String studentid;

    @Column(nullable = false)
    private String dob;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String motherName;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private Long age;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private double marksSSLC;

    @Column(nullable = false)
    private double marksHSC;

    @OneToOne(mappedBy = "student")
    private User user;
    

    @OneToOne
    private Admission admission;

    public String getStudentid() {
        return studentid;
    }





    
    
}
