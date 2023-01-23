package com.backfcdev.personal_task_management.service;

import com.backfcdev.personal_task_management.model.Task;
import com.backfcdev.personal_task_management.model.dto.TaskDTO;

import java.util.List;

public interface ITaskService {

    List<Task> getAll();
    Task save(TaskDTO taskDTO);
    Task getById(long id);
    Task update(long id, TaskDTO taskDTO);
    List<Task> getAllTasksAccordingToStatus(String status);
    Task markTaskAccomplished(long id);
    long deleteById(long id);
}
