package com.example.demo.controller;

import com.example.demo.entity.Job;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JobController {
    @Autowired
    private JobService jobService;
    @PostMapping("post-job")
    public ResponseEntity<?>addJob(@RequestBody Job job){
        return jobService.postJob(job);
    }

    @GetMapping("get-title/{title}")
    public ResponseEntity<?>findTitle(@PathVariable("title") String title){
        return jobService.findTitle(title);
    }

//    @GetMapping("find-applicant/{title}")
//    public ResponseEntity<?>findApplicantTitle(@PathVariable("title") String title){
//        return jobService.findApplicantsByTitle(title);
//    }
    @GetMapping("get-jobs")
    public ResponseEntity<?>getJobs(){
        return jobService.getJobs();
    }
}
