package com.example.demo.service;

import com.example.demo.entity.Passport;
import com.example.demo.repository.PassportRepository;
import com.example.demo.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassportService {
    @Autowired
    private PassportRepository passportRepository;
    public byte[] downloadImage(String fileName){
        Optional<Passport> dbImageData = passportRepository.findByName(fileName);
        byte[] images= DocumentUtils.decompressImage(dbImageData.get().getPicture());
        return images;
    }
}
