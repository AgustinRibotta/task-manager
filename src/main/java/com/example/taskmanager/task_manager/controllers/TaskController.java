package com.example.taskmanager.task_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.services.ITaskService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAll () {
        return ResponseEntity.ok(this.taskService.getAll());
    }
    
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.getById(id));
    }
    
    @PostMapping("/task/new")
    public ResponseEntity<TaskDto> post (@RequestBody TaskDto taskDto) {
        
        TaskDto newTask = this.taskService.post(taskDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newTask.getId())
            .toUri();

        return ResponseEntity.created(location).body(newTask);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDto> put(@PathVariable Long id, @RequestBody TaskDto taskDto) {        
        return ResponseEntity.ok(this.taskService.put(taskDto, id));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
