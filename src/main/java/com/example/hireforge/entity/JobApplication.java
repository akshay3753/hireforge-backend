package com.example.hireforge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobTitle;

    private String location;

    private Integer salaryMin;

    private Integer salaryMax;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;


    private LocalDate appliedDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
