package com.example.hireforge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateJobRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String jobTitle;

    private String location;

    private Integer salaryMin;

    private Integer salaryMax;
}
