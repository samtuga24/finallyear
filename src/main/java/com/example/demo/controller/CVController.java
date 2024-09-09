package com.example.demo.controller;

import com.example.demo.service.ApplicantCVService;
import com.example.demo.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/display")
public class CVController {
    @Autowired
    private ApplicantCVService applicantService;

    @GetMapping("/{cv}")
    public ResponseEntity<?> downloadImage(@PathVariable String cv){
        byte[] imageData=applicantService.downloadImage(cv);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .body(imageData);

    }
}
