package com.example.demo.repository;

import com.example.demo.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    Optional<Passport> findByName(String fileName);
    Optional<Passport>findByAdminId(Long id);
}
