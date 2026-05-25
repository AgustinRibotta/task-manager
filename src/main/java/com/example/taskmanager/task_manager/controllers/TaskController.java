package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
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
    @Tag(name = "Task Controller", description = "Task management")
    @Operation(
            summary = "Get all task",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDto>> getAll () {
        return ResponseEntity.ok(this.taskService.getAll());
    }

    // GET BY ID - 200 OK - [] - 401 Unauthorized
    @Tag(name = "Task Controller", description = "Task management")
    @Operation(
            summary = "Get task by ID",
            description = "Requires authentication with JWT and ADMIN role  or be the owner"
    )
    @PreAuthorize("@securityConfigTask.isTask(#id) or hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.getById(id));
    }
    
    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @Tag(name = "Task Controller", description = "Task management")
    @Operation(
            summary = "Create task",
            description = "Requires authentication with JWT and ADMIN or MANAGER role"
    )
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @PostMapping("/task/new")
    public ResponseEntity<TaskResponseDto> post (@Valid @RequestBody TaskRequestDto request) {
        
        TaskResponseDto response = this.taskService.post(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();

        return ResponseEntity.created(location).body(response);
    }

    // PUT - 200 OK - 404 Not Found
    @Tag(name = "Task Controller", description = "Task management")
    @Operation(
            summary = "Update task by ID",
            description = "Requires authentication with JWT and ADMIN role or MANAGER role or be the owner"
    )
    @PreAuthorize("@securityConfigTask.isTask(#id) or hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> put(@PathVariable Long id, @Valid @RequestBody TaskRequestDto request) {
        return ResponseEntity.ok(this.taskService.put(request, id));
    }

    // DELETE - 204 No Content - 404 Not Found
    @Tag(name = "Task Controller", description = "Task management")
    @Operation(
            summary = "Delete task by ID",
            description = "Requires authentication with JWT and ADMIN or MANAGER role "
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
