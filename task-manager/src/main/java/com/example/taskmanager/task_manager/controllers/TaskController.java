package com.example.taskmanager.task_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.dtos.TaskDto;
import com.example.taskmanager.task_manager.services.ITaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // GET - 200 OK - [] - 401 Unauthorized
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAll () {
        return ResponseEntity.ok(this.taskService.getAll());
    }

    // GET - 200 OK - [] - 401 Unauthorized
    @PreAuthorize("@securityConfigTask.isTask(#id) or hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.getById(id));
    }
    
    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    @PostMapping("/task/new")
    public ResponseEntity<TaskDto> post (@Valid @RequestBody TaskDto taskDto) {
        
        TaskDto newTask = this.taskService.post(taskDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newTask.getId())
            .toUri();

        return ResponseEntity.created(location).body(newTask);
    }

    // PUT - 200 OK - 404 Not Found
    @PreAuthorize("@securityConfigTask.isTask(#id) or hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDto> put(@PathVariable Long id,@Valid @RequestBody TaskDto taskDto) {        
        return ResponseEntity.ok(this.taskService.put(taskDto, id));
    }

    // DELETE - 204 No Content - 404 Not Found
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
