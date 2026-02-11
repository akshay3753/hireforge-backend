package com.example.hireforge.controller;

import com.example.hireforge.dto.JobApplicationResponse;
import com.example.hireforge.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final JobApplicationRepository jobApplicationRepository;

    @GetMapping("/applications")
    @PreAuthorize("hasRole('ADMIN')")
    public Object getAllApplications() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(app -> JobApplicationResponse.builder()
                        .id(app.getId())
                        .companyName(app.getCompanyName())
                        .jobTitle(app.getJobTitle())
                        .status(app.getStatus())
                        .appliedDate(app.getAppliedDate())
                        .createdAt(app.getCreatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
