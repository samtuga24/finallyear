package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Applicant {
    @SequenceGenerator(name = "applicant_sequence", sequenceName = "applicant_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "applicant_sequence")
    @Id
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private LocalDate dateApplied;
    private String phoneNumber;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ApplicantCV ApplicantCV;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Job job;

    public Applicant(String fname, String lname, String email, LocalDate dateApplied, String phoneNumber, Job job) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.dateApplied = dateApplied;
        this.phoneNumber = phoneNumber;
        this.job = job;
    }
}
