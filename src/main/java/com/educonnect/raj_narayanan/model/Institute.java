package com.educonnect.raj_narayanan.model;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.UUID;

import java.sql.Blob;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "institute")
public class Institute {
    @Id
    @GeneratedValue(strategy = UUID)
    private String instituteid;

    @Column(nullable = false)
    private String institutename;

    @Column(nullable = true)
    private Blob institutelogo;

    @Column(nullable = false)
    private String institutedescription;

    @Column(nullable = false)
    private String instituteaddress;

    @Column(nullable = false)
    private long institutephone;

    @Column(nullable = false)
    private String instituteemail;

    @Column(nullable = false)
    private String institutewebsite;

    @Column(nullable = false)
    private long noofcoursesavailale;

    @OneToMany(mappedBy = "institute", fetch = LAZY, cascade = ALL)
    private List<Courses> courses;

    

    
    
}
