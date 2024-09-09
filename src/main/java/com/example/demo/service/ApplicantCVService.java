package com.example.demo.service;

import com.example.demo.entity.ApplicantCV;
import com.example.demo.repository.ApplicantCVRepository;
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
    public byte[] downloadImage(String fileName){
        Optional<ApplicantCV> dbImageData = applicantCVRepository.findByName(fileName);
        byte[] images= DocumentUtils.decompressImage(dbImageData.get().getPicture());
        return images;
    }
}
