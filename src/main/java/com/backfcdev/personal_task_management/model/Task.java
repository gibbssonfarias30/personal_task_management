package com.backfcdev.personal_task_management.model;

import com.backfcdev.personal_task_management.model.enums.Finished;
import com.backfcdev.personal_task_management.model.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "date_creation")
    private LocalDate dateCreation;
    @Column(name = "estimated_completion_date")
    private LocalDate estimatedCompletionDate;
    @Enumerated(EnumType.STRING)
    private Finished finished = Finished.NO;
    @Enumerated(EnumType.STRING)
    private Status status;

    @PrePersist
    public void addDateCreation(){
        dateCreation = LocalDate.now();
    }
}
