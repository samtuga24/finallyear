package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ApplicantCVRepository;
import com.example.demo.repository.PassportRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private PassportRepository passportRepository;
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
            admin.setJobStatus("none");
            admin.setStatus("Active");
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

    public ResponseEntity<?>updateJob(String uname,  Job job){
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        Set<Job> jobs = admin.getJobs();
        jobs.add(job);
        if(admin == null){
            return ResponseEntity.ok().body("user not found");
        }
        admin.setJobs(jobs);
        admin.setDateApplied(LocalDate.now());
        adminRepository.save(admin);
        return ResponseEntity.ok().body("Application successful");

    }

    public ResponseEntity<?>getApplicants(){
        return ResponseEntity.ok().body(adminRepository.getApplicants());
    }

    public ResponseEntity<?>getApplicantsByEMail(String uname){
        return ResponseEntity.ok().body(adminRepository.findByUname(uname));
    }

    public ResponseEntity<?>updateJobStatus(String uname){
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        if( admin == null){
           return ResponseEntity.ok().body("user not found");
        }
        admin.setJobStatus("accepted");
        adminRepository.save(admin);
        return ResponseEntity.ok().body("user has been accepted");
    }
    public ResponseEntity<?>getAccepted(String uname){
        return ResponseEntity.ok().body(adminRepository.getStatus(uname));
    }
    @Transactional
    public ResponseEntity<?>updateUsers(String uname, String fname, String lname, String email, MultipartFile file) throws IOException {
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        if(admin == null){
            return ResponseEntity.ok().body("user not found");
        }
        if(passportRepository.findByName(file.getOriginalFilename()).isPresent()){

            return ResponseEntity.ok().body("Please Rename file");
        }

        Passport passport = Passport.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .adminId(admin.getId())
                .picture(DocumentUtils.compressImage(file.getBytes())).build();
            admin.setPassport(passport);
            admin.setImageName(passport.getName().length() > 0 ? passport.getName() : admin.getImageName());
        passport.setPicture(DocumentUtils.compressImage(file.getBytes()));
        passport.setName(file.getOriginalFilename());
        passport.setType(file.getContentType());
        admin.setPassport(passport);
        admin.setImageName(passport.getName().length() > 0 ? passport.getName() : admin.getImageName());
        admin.setFname(fname.length() > 0 ? fname : admin.getFname());
        admin.setLname(lname.length() > 0 ? lname : admin.getLname());
        admin.setUname(email.length() > 0 ? email : admin.getUname());

        adminRepository.save(admin);
        return ResponseEntity.ok().body("profile picture updated successfully");
    }

    public  ResponseEntity<?>viewAll(){
        return ResponseEntity.ok().body(adminRepository.findAll());
    }

    public ResponseEntity<?>updatePassword(String uname, String pass, String newPass){
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        if(admin == null) {
            return ResponseEntity.ok().body("user cannot be found");
        }

        if(bCryptPasswordEncoder.matches(pass, admin.getPassword())){
            admin.setPassword(bCryptPasswordEncoder.encode(newPass));
            adminRepository.save(admin);
            return ResponseEntity.ok().body("Password updated successfully");
        } else{
            return ResponseEntity.ok().body("Password is incorrect");
        }

    }

    public ResponseEntity<?>updateStatus(String uname){
        Admin admin = adminRepository.findByUname(uname).orElse(null);
        if(admin == null) {
            return ResponseEntity.ok().body("user cannot be found");
        }
        admin.setStatus("inactive");
        adminRepository.save(admin);
        return ResponseEntity.ok().body("User status has been updated");
    }
}
