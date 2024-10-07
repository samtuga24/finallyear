package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Job;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("add-admin")
    public ResponseEntity<?>addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @RequestMapping( value = "update-job/{email}", method= RequestMethod.POST)
    public ResponseEntity<?>updateJob(@PathVariable("email") String email, @RequestBody Job job){
        return adminService.updateJob(email, job);
    }
    @RequestMapping( value = "apply/{email}", method= RequestMethod.PATCH)
    public ResponseEntity<?> applyJob(@PathVariable("email") String email, @RequestParam("applicantCV") MultipartFile applicantCV) throws IOException {
        return adminService.updateCV(email, applicantCV);
    }

    @RequestMapping( value = "update/{email}", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateUser(@PathVariable("email") String email, @RequestParam("fname") String fname, @RequestParam("lname") String lname, @RequestParam("uname") String uname,@RequestParam("passport") MultipartFile passport) throws IOException {
        return adminService.updateUsers(email, fname, lname, uname, passport);
    }

    @GetMapping("get-applicants")
    public ResponseEntity<?> getApplicant(){
        return adminService.getApplicants();
    }
    @GetMapping("get-applicants/{email}")
    public ResponseEntity<?> getApplicantByEmail(@PathVariable("email")String email){
        return adminService.getApplicantsByEMail(email);
    }

    @PatchMapping("update-status/{email}")
    public ResponseEntity<?> updateStatus(@PathVariable("email")String email){
        return adminService.updateJobStatus(email);
    }

    @GetMapping("get-accepted/{email}")
    public ResponseEntity<?> getAccepted(@PathVariable("email") String email){
        return adminService.getAccepted(email);
    }

    @GetMapping("view-all")
    public ResponseEntity<?> viewAll(){
        return adminService.viewAll();
    }

    @PatchMapping("update-password/{uname}")
    public ResponseEntity<?>updatePassword(@PathVariable("uname") String uname, @RequestParam("password") String password, @RequestParam("newPass") String newPass){
        return ResponseEntity.ok().body(adminService.updatePassword(uname,password,newPass));
    }

    @PatchMapping("status/{uname}")
    public ResponseEntity<?>update(@PathVariable("uname") String uname){
        return ResponseEntity.ok().body(adminService.updateStatus(uname));
    }
}
