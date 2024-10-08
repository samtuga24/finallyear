package com.example.demo.service;

import com.example.demo.entity.ApplicantCV;
import com.example.demo.entity.Passport;
import com.example.demo.repository.ApplicantCVRepository;
import com.example.demo.repository.PassportRepository;
import com.example.demo.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service
public class ApplicantCVService {
    @Autowired
    private ApplicantCVRepository applicantCVRepository;

    @Autowired
    private PassportRepository passportRepository;
    public byte[] downloadImage(String fileName){
        Optional<ApplicantCV> dbImageData = applicantCVRepository.findByName(fileName);
        byte[] images= DocumentUtils.decompressImage(dbImageData.get().getPicture());
        return images;
    }

    public byte[] downloadPass(String fileName){
        Optional<Passport> dbImageData = passportRepository.findByName(fileName);
        byte[] images= DocumentUtils.decompressImage(dbImageData.get().getPicture());
        return images;
    }
}
