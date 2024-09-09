package com.example.demo.repository;

import com.example.demo.entity.Applicant;
import com.example.demo.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByTitle(String title);
}
