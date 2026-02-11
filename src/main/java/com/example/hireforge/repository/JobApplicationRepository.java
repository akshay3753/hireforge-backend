package com.example.hireforge.repository;

import com.example.hireforge.entity.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {

    Page<JobApplication> findByUserId(UUID userId, Pageable pageable);
}
