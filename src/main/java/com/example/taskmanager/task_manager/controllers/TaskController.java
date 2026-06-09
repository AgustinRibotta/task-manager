package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskUpdateRequest;
import com.example.taskmanager.task_manager.dtos.user.UsersAssignRequestDto;
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

    // CRUD
    @PreAuthorize("hasAuthority('tasks:read:all')")
    @GetMapping()
    public ResponseEntity<List<TaskResponseDto>> getAll () {
        return ResponseEntity.ok(this.taskService.findAll());
    }

    @PreAuthorize("@securityConfigTask.isTask(#id) or hasAuthority('tasks:read')")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.findById(id));
    }

    @PreAuthorize("@securytiConfigUser.isUser(#id)")
    @GetMapping("/users/{id}/tasks")
    public ResponseEntity<List<TaskResponseDto>> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(this.taskService.findByUsersId(id));
    }

    @PreAuthorize("hasAuthority('tasks:create')")
    @PostMapping()
    public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody TaskRequestDto request) {

        TaskResponseDto response = this.taskService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PreAuthorize("@securityConfigTask.isTask(#id) or hasAuthority('tasks:update')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        return ResponseEntity.ok(this.taskService.update(request, id));
    }

    @PreAuthorize("hasAuthority('tasks:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // USERS
    @PreAuthorize(" hasAuthority('tasks:update')")
    @PostMapping("{taskId}/users")
    public ResponseEntity<?> assignUsers (@PathVariable Long taskId, @RequestBody UsersAssignRequestDto request){
        this.taskService.assignUsersToProject(taskId, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize(" hasAuthority('tasks:update')")
    @PostMapping("{taskId}/users/{userId}")
    public ResponseEntity<?> removeUsers (@PathVariable Long taskId, @PathVariable Long userId){
        this.taskService.removeUsersFromProject(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}
