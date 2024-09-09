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
public class Admin {
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "admin_sequence")
    @Id
    private long id;
    private String fname;
    private String lname;
    private String uname;
    private String password;
    private LocalDate dateApplied;
    private String phoneNumber;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Staff> staff = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Roles", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles = new HashSet<>();

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ApplicantCV ApplicantCV;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Job> jobs = new HashSet<>();

    public Admin(String fname, String lname, String uname, String password, LocalDate dateApplied, String phoneNumber) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.password = password;
        this.dateApplied = dateApplied;
        this.phoneNumber = phoneNumber;
    }
}
