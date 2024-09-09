package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Applicant;
import com.example.demo.entity.Job;
import com.example.demo.service.AdminService;
import com.example.demo.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

//    @PostMapping("create-account")
//    public ResponseEntity<?> createApplicant(@RequestBody Applicant applicant){
//        return applicantService.createApplicant(applicant);
//    }
//
//    @RequestMapping( value = "apply/{email}", method= RequestMethod.PATCH)
//    public ResponseEntity<?> applyJob(@PathVariable("email") String email, @RequestParam("Job") Job job ,@RequestParam("applicantCV") MultipartFile applicantCV) throws IOException {
//        return applicantService.updateJob(email,job, applicantCV);
//    }

//    @PatchMapping("apply/{title}")
//    public ResponseEntity<?> applyJob(@PathVariable("title")String title,@RequestBody Applicant applicant){
//        return applicantService.applyJob(applicant,title);
//    }

//    @GetMapping("get-applicants")
//    public ResponseEntity<?> getApplicant(){
//        return applicantService.getApplicants();
//    }
}
