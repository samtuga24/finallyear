package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("add-staff")
    public ResponseEntity<?>addStaff(@RequestBody Staff staff){
        return staffService.addStaff(staff);
    }

    @GetMapping("get-staff")
    public ResponseEntity<?>findStaff(){
        return staffService.getStaff();
    }
}
