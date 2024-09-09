package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Staff {
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "staff_sequence")
    @Id
    private long id;
    private String fname;
    private String lname;
    private String jobTitle;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Admin admin;

    public Staff(String fname, String lname, String jobTitle, String email) {
        this.fname = fname;
        this.lname = lname;
        this.jobTitle = jobTitle;
        this.email = email;
    }
}
