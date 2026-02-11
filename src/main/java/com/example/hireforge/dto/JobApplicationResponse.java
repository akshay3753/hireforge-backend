package com.example.hireforge.dto;

import com.example.hireforge.entity.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class JobApplicationResponse {

    private UUID id;
    private String companyName;
    private String jobTitle;
    private ApplicationStatus status;
    private LocalDate appliedDate;
    private String location;
    private Integer salaryMin;
    private Integer salaryMax;
    private LocalDateTime createdAt;
}
