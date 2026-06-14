package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.task.TaskRequestDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;
import com.example.taskmanager.task_manager.dtos.user.UsersAssignRequestDto;
import com.example.taskmanager.task_manager.services.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.services.IProjectService;

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

@Tag(name = "Projects")
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectService projectService;

    @Operation(summary = "Find all")
    @PreAuthorize("hasAuthority('projects:read:all')")
    @GetMapping("")
    public ResponseEntity<List<ProjectResponseDto>> getAll() {
        return ResponseEntity.ok(this.projectService.findAll());
    }

    @Operation(summary = "Find By id")
    @PreAuthorize("@securityConfigProject.isProject(#id) or hasAuthority('projects:read')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getById(@PathVariable Long id) {
        ProjectResponseDto response = this.projectService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find By User id")
    @PreAuthorize("@securytiConfigUser.isUser(#id)")
    @GetMapping("/user/{id}/projects")
    public ResponseEntity<List<ProjectResponseDto>> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(this.projectService.findByUsersId(id));
    }

    @Operation(summary = "Create New")
    @PreAuthorize("hasAuthority('projects:create')")
    @PostMapping()
    public ResponseEntity<ProjectResponseDto> create(@Valid @RequestBody ProjectRequestDto request) {
        ProjectResponseDto response = this.projectService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        return ResponseEntity.created(location).body(response);
    }


    @Operation(summary = "Update")
    @PreAuthorize("hasAuthority('projects:update')")
    @PutMapping("{id}")
    public ResponseEntity<ProjectResponseDto> update(@PathVariable Long id, @RequestBody ProjectRequestDto request) {
        ProjectResponseDto response = this.projectService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete")
    @PreAuthorize("hasAuthority('projects:delete')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Change propietari")
    @PreAuthorize("hasAuthority('projects:manager')")
    @PostMapping("{projectId}/owner/{ownerId}")
    public ResponseEntity<?> changeOwner(@PathVariable Long projectId, @PathVariable Long ownerId) {
        this.projectService.changeOwner(projectId, ownerId);
        return ResponseEntity.ok().build();
    }

    // USERS
    @Operation(summary = "Assign User")
    @PreAuthorize("hasAuthority('projects:users')")
    @PostMapping("{projectId}/users")
    public ResponseEntity<?> assignUsers(@PathVariable Long projectId, @RequestBody UsersAssignRequestDto request) {
        this.projectService.assignUsersToProject(projectId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove User")
    @PreAuthorize("hasAuthority('projects:users')")
    @DeleteMapping("{projectId}/users/{userId}")
    public ResponseEntity<?> removeUserProject(@PathVariable Long projectId, @PathVariable Long userId) {
        this.projectService.removeUserFromAllProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
