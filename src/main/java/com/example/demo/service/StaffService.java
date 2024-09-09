package com.example.demo.service;

import com.example.demo.entity.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;
    public ResponseEntity<?>addStaff(Staff staff){
        Staff staff1 = staffRepository.findByEmail(staff.getEmail()).orElse(null);
        if(staff1 == null){
            staff.setAdmin(staff.getAdmin());
            staffRepository.save(staff);
            return ResponseEntity.ok().body("Staff added successfully");
        }else{
            return ResponseEntity.ok().body("Staff already exist");
        }
    }

    public ResponseEntity<?>getStaff(){
        return ResponseEntity.ok().body(staffRepository.findAll());
    }
}
