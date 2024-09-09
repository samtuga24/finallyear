package com.example.demo.repository;

import com.example.demo.entity.ApplicantCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantCVRepository extends JpaRepository<ApplicantCV, Long> {
    Optional<ApplicantCV> findByName(String fileName);
}
