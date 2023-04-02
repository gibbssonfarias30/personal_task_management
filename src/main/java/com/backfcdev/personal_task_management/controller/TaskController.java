package com.backfcdev.personal_task_management.controller;

import com.backfcdev.personal_task_management.model.Task;
import com.backfcdev.personal_task_management.model.dto.TaskDTO;
import com.backfcdev.personal_task_management.service.ITaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final ITaskService taskService;
    private final ModelMapper mapper;

    @GetMapping
    ResponseEntity<List<Task>> findAll(){
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<Task> saveTask(@RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.save(taskDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> findTaskById(@PathVariable long id){
        return new ResponseEntity<>(taskService.getById(id), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.update(id, taskDTO), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    ResponseEntity<List<Task>> getAllTasksAccordingToStatus(@PathVariable String status){
        return new ResponseEntity<>(taskService.getAllTasksAccordingToStatus(status),
                                    HttpStatus.OK);
    }

    @GetMapping("/accomplished/{id}")
    ResponseEntity<Task> markTaskAccomplished(@PathVariable long id){
        return new ResponseEntity<>(taskService.markTaskAccomplished(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteTaskById(@PathVariable long id){
        return new ResponseEntity<>(taskService.deleteById(id), HttpStatus.NO_CONTENT);
    }



    public Task convertToEntity(TaskDTO dto){
        return mapper.map(dto, Task.class);
    }

    public TaskDTO convertToDto(Task entity){
        return mapper.map(entity, TaskDTO.class);
    }
}
