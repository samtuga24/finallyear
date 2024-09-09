package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Applicant;
import com.example.demo.entity.ApplicantCV;
import com.example.demo.entity.Job;
import com.example.demo.repository.ApplicantCVRepository;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.repository.JobRepository;
import com.example.demo.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicantCVRepository applicantCVRepository;
    @Autowired
    private JobRepository jobRepository;
    public ResponseEntity<?>createApplicant(Applicant applicant){
        Applicant app = applicantRepository.findByEmail(applicant.getEmail()).orElse(null);
        if(app == null){
            applicant.setDateApplied(LocalDate.now());
            applicant.setJob(applicant.getJob());
            applicantRepository.save(applicant);
            return ResponseEntity.ok().body("account created successfully");
        }else{
            return ResponseEntity.ok().body("email taken");
        }
    }

    public ResponseEntity<?>updateJob(String email, Job job, MultipartFile file) throws IOException {
        Applicant applicant = applicantRepository.findByEmail(email).orElse(null);
        if(applicant == null){
            return ResponseEntity.ok().body("user not found");
        }
        if(applicantCVRepository.findByName(file.getOriginalFilename()).isPresent()){
            return ResponseEntity.ok().body("Please Rename file");
        }

        ApplicantCV applicantCV = ApplicantCV.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .picture(DocumentUtils.compressImage(file.getBytes())).build();
            applicant.setJob(job);
            applicant.setApplicantCV(applicantCV);
            applicantRepository.save(applicant);
            return ResponseEntity.ok().body("Application successful");

    }


    public ResponseEntity<?>getApplicants(){
        return ResponseEntity.ok().body(applicantRepository.findAll());
    }

}
