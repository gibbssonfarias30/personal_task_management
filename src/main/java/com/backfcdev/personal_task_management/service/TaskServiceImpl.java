package com.backfcdev.personal_task_management.service;

import com.backfcdev.personal_task_management.exception.TaskNotFoundException;
import com.backfcdev.personal_task_management.model.Task;
import com.backfcdev.personal_task_management.model.enums.Finished;
import com.backfcdev.personal_task_management.repository.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {
    private final ITaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task update(long id, Task task) {
        taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasksAccordingToStatus(String status) {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getStatus().name().equalsIgnoreCase(status))
                .collect(toList());
    }

    @Override
    public Task markTaskAccomplished(long id) {
        Task taskAccomplished = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskAccomplished.setFinished(Finished.YES);
        return taskRepository.save(taskAccomplished);
    }

    @Override
    public void delete(long id) {
        taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.deleteById(id);
    }
}
