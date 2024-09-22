package com.example.demo.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @SequenceGenerator(name = "job_sequence", sequenceName = "job_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "job_sequence")
    @Id
    private Long id;
    private String title;
    private String description;
    private String qualification;
    private double salary;
    private String experience;
    private LocalDate deadline;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Admin admin;

    public Job(String title, String description, String qualification, double salary, String experience, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.qualification = qualification;
        this.salary = salary;
        this.experience = experience;
        this.deadline = deadline;
    }
}
