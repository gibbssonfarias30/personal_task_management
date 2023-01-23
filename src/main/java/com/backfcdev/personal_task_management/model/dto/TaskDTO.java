package com.backfcdev.personal_task_management.model.dto;

import com.backfcdev.personal_task_management.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private String description;

    @Column(name = "estimated_completion_date")
    private LocalDate estimatedCompletionDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
