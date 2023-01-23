package com.backfcdev.personal_task_management.service;

import com.backfcdev.personal_task_management.exception.TaskNotFoundException;
import com.backfcdev.personal_task_management.model.Task;
import com.backfcdev.personal_task_management.model.dto.TaskDTO;
import com.backfcdev.personal_task_management.model.enums.Finished;
import com.backfcdev.personal_task_management.repository.ITaskRepository;
import com.backfcdev.personal_task_management.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements ITaskService {
    private final ITaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
    @Override
    public Task save(TaskDTO taskDTO) {
        Task task = Mapper.modelMapper().map(taskDTO, Task.class);
        return taskRepository.save(task);
    }

    @Override
    public Task getById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task update(long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        Mapper.modelMapper().map(taskDTO, task);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasksAccordingToStatus(String status) {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getStatus().name().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    @Override
    public Task markTaskAccomplished(long id) {
        Task taskAccomplished = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskAccomplished.setFinished(Finished.YES);
        return taskRepository.save(taskAccomplished);
    }

    @Override
    public long deleteById(long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.deleteById(task.getId());
        return task.getId();
    }
}
