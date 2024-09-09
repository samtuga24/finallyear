package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ApplicantCVRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ApplicantCVRepository applicantCVRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?>addAdmin(Admin admin){
        Admin ad = adminRepository.findByUname(admin.getUname()).orElse(null);
        if( ad == null){

            Set<Role> userRole = new HashSet<>();
            admin.getRoles().stream()
                    .forEach(role->{
                        Role user_role = roleRepository.findRoleByName(role.getName()).orElse(null);
                        if(user_role == null) {
                            user_role = new Role();
                        }
                        user_role.setName(role.getName());
                        userRole.add(user_role);
                    });

            admin.setDateApplied(LocalDate.now());
            admin.setJob(admin.getJob());
            admin.setRoles(userRole);
            admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);
            return ResponseEntity.ok().body("Admin added successfully");
        }else{
            return ResponseEntity.ok().body("Admin account already exists");
        }
    }

    public ResponseEntity<?>updateCV(String uname, MultipartFile file) throws IOException {
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        if(admin == null){
            return ResponseEntity.ok().body("user not found");
        }
        if(applicantCVRepository.findByName(file.getOriginalFilename()).isPresent()){
            return ResponseEntity.ok().body("Please Rename file");
        }

        ApplicantCV applicantCV = ApplicantCV.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .picture(DocumentUtils.compressImage(file.getBytes())).build();
        admin.setApplicantCV(applicantCV);
        adminRepository.save(admin);
        return ResponseEntity.ok().body("CV updated successfully");

    }

    public ResponseEntity<?>updateJob(String uname, Job job){
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        if(admin == null){
            return ResponseEntity.ok().body("user not found");
        }

        admin.setJob(job);
        admin.setDateApplied(LocalDate.now());
        adminRepository.save(admin);
        return ResponseEntity.ok().body("Application successful");

    }

    public ResponseEntity<?>getApplicants(){
        return ResponseEntity.ok().body(adminRepository.findAll());
    }

    public ResponseEntity<?>getApplicantsByEMail(String uname){
        return ResponseEntity.ok().body(adminRepository.findByUname(uname));
    }
}
