package com.example.demo.service;

import com.example.demo.entity.Job;
import com.example.demo.repository.ApplicantRepository;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    public ResponseEntity<?>postJob(Job job){
        job.setAdmin(job.getAdmin());
        jobRepository.save(job);
        return ResponseEntity.ok().body("Job posted Successfully");
    }

    public ResponseEntity<?>findTitle(String title){
        return ResponseEntity.ok().body(jobRepository.findByTitle(title));
    }

//    public ResponseEntity<?>findApplicantsByTitle(String title){
//        return ResponseEntity.ok().body(applicantRepository.findByJobTitle(title));
//    }

    public ResponseEntity<?>getJobs(){
        return ResponseEntity.ok().body(jobRepository.findAll());
    }
}
