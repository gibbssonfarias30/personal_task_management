package com.backfcdev.personal_task_management.repository;

import com.backfcdev.personal_task_management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {
}
