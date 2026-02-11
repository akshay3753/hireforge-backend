package com.example.hireforge.service;

import com.example.hireforge.dto.CreateJobApplicationRequest;
import com.example.hireforge.dto.JobApplicationResponse;
import com.example.hireforge.entity.ApplicationStatus;
import com.example.hireforge.entity.JobApplication;
import com.example.hireforge.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    // CREATE APPLICATION
    public JobApplicationResponse create(UUID userId, CreateJobApplicationRequest request) {

        JobApplication application = JobApplication.builder()
                .userId(userId)
                .companyName(request.getCompanyName())
                .jobTitle(request.getJobTitle())
                .status(ApplicationStatus.valueOf(request.getStatus()))
                .appliedDate(LocalDate.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        JobApplication saved = jobApplicationRepository.save(application);

        return mapToResponse(saved);
    }


    // GET PAGINATED
    public Page<JobApplicationResponse> getUserApplications(UUID userId, int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return jobApplicationRepository
                .findByUserId(userId, pageable)
                .map(this::mapToResponse);
    }

    private JobApplicationResponse mapToResponse(JobApplication app) {
        return JobApplicationResponse.builder()
                .id(app.getId())
                .companyName(app.getCompanyName())
                .jobTitle(app.getJobTitle())
                .location(app.getLocation())
                .salaryMin(app.getSalaryMin())
                .salaryMax(app.getSalaryMax())
                .status(app.getStatus())
                .appliedDate(app.getAppliedDate())
                .createdAt(app.getCreatedAt())
                .build();
    }

    // UPDATE APPLICATION
    public JobApplicationResponse update(
            UUID userId,
            UUID applicationId,
            CreateJobApplicationRequest request
    ) {

        JobApplication application = jobApplicationRepository
                .findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!application.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setStatus(ApplicationStatus.valueOf(request.getStatus()));
        application.setUpdatedAt(LocalDateTime.now());

        JobApplication saved = jobApplicationRepository.save(application);

        return mapToResponse(saved);
    }
    public void delete(UUID userId, UUID applicationId) {

        JobApplication application = jobApplicationRepository
                .findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!application.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        jobApplicationRepository.delete(application);
    }

}
