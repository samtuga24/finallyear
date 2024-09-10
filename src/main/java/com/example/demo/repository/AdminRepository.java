package com.example.demo.repository;

import com.example.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    public static final String GET_APPLICANTS = "SELECT * FROM admin WHERE id>1";
    @Query(value = GET_APPLICANTS, nativeQuery = true)
    List<Admin> getApplicants();

    public static final String GET_STATUS = "SELECT * FROM admin WHERE uname=?1 AND job_status='accepted'";
    @Query(value = GET_STATUS, nativeQuery = true)
    List<Admin> getStatus(@Param("uname") String uname);
    Optional<Admin>findByUname(String uname);
}
