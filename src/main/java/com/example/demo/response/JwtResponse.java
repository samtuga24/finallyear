package com.example.demo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private long id;
    private String uname;
    private String password;
    private String fname;
    private String lname;
    private String phoneNumber;
    private LocalDate dateApplied;
    private String imageName;
    private String status;
    private List<String> roles;

    public JwtResponse(String token, long id, String fname, String lname, String uname, String phoneNumber, LocalDate dateApplied, String imageName, String status, List<String> roles) {
        this.token = token;
        this.id = id;
        this.fname = fname;
        this.uname = uname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
        this.dateApplied = dateApplied;
        this.imageName = imageName;
        this.status = status;
        this.roles = roles;
    }
}
