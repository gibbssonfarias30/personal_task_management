package com.backfcdev.personal_task_management.controller;

import com.backfcdev.personal_task_management.model.Task;
import com.backfcdev.personal_task_management.model.dto.TaskDTO;
import com.backfcdev.personal_task_management.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final ITaskService taskService;
    private final ModelMapper mapper;

    @GetMapping
    ResponseEntity<List<TaskDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.save(convertToEntity(taskDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(task));
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(convertToDto(taskService.findById(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<TaskDTO> update(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.update(id, convertToEntity(taskDTO));
        return ResponseEntity.ok(convertToDto(task));
    }

    @GetMapping("/status/{status}")
    ResponseEntity<List<TaskDTO>> getAllTasksAccordingToStatus (@PathVariable String status){
        return ResponseEntity.ok(taskService.getAllTasksAccordingToStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/accomplished/{id}")
    ResponseEntity<TaskDTO> markTaskAccomplished(@PathVariable long id){
        return ResponseEntity.ok(convertToDto(taskService.markTaskAccomplished(id)));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById (@PathVariable long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }


    public Task convertToEntity (TaskDTO dto){
        return mapper.map(dto, Task.class);
    }

    public TaskDTO convertToDto (Task entity){
        return mapper.map(entity, TaskDTO.class);
    }
}
