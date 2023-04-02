package com.backfcdev.personal_task_management.service;

import com.backfcdev.personal_task_management.model.Task;

import java.util.List;

public interface ITaskService {

    List<Task> findAll();
    Task save(Task task);
    Task findById(long id);
    Task update(long id, Task task);
    List<Task> getAllTasksAccordingToStatus(String status);
    Task markTaskAccomplished(long id);
    void delete(long id);
}
