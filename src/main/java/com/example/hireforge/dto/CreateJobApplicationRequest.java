package com.example.hireforge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateJobApplicationRequest {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @NotBlank(message = "Job title is required")
    @Size(min = 2, max = 100, message = "Job title must be between 2 and 100 characters")
    private String jobTitle;

    @NotBlank(message = "Status is required")
    @Pattern(
        regexp = "APPLIED|SCREENING|INTERVIEW|OFFER|ACCEPTED|REJECTED|WITHDRAWN",
        message = "Status must be one of: APPLIED, SCREENING, INTERVIEW, OFFER, ACCEPTED, REJECTED, WITHDRAWN"
    )
    private String status;
}
