package com.example.hireforge.controller;

import com.example.hireforge.dto.CreateJobApplicationRequest;
import com.example.hireforge.dto.JobApplicationResponse;
import com.example.hireforge.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    // CREATE
    @PostMapping
    public JobApplicationResponse create(
            Authentication authentication,
            @jakarta.validation.Valid @RequestBody CreateJobApplicationRequest request

    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        return jobApplicationService.create(userId, request);
    }

    // GET PAGINATED
    @GetMapping
    public Page<JobApplicationResponse> getAll(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        return jobApplicationService.getUserApplications(userId, page, size);
    }

    // UPDATE
    @PutMapping("/{id}")
    public JobApplicationResponse update(
            Authentication authentication,
            @PathVariable UUID id,
            @RequestBody CreateJobApplicationRequest request
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        return jobApplicationService.update(userId, id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(
            Authentication authentication,
            @PathVariable UUID id
    ) {
        UUID userId = (UUID) authentication.getPrincipal();
        jobApplicationService.delete(userId, id);
    }
}
