package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
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

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectService projectService;

    // GET - 200 OK - [] - 401 Unauthorized
    @PreAuthorize("hasAuthority('projects:read:all')")
    @GetMapping("")
    public ResponseEntity<List<ProjectResponseDto>> getAll() {
        return ResponseEntity.ok(this.projectService.getAll());
    }

    // GET - 200 OK - [] - 401 Unauthorized
    @PreAuthorize("@securityConfigProject.isProject(#id) or hasAuthority('projects:read')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getById(@PathVariable Long id) {
        ProjectResponseDto response = this.projectService.getById(id);
        return ResponseEntity.ok(response);
    }

    // GET - 200 OK - [] - 401 Unauthorized
    @PreAuthorize("@securytiConfigUser.isUser(#id)")
    @GetMapping("/userd/{id}/projects")
    public ResponseEntity<List<ProjectResponseDto>> projectByUserId (@PathVariable Long id) {
        return ResponseEntity.ok(this.projectService.findByUsersId(id));
    }

    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @PreAuthorize("hasAuthority('projects:create')")
    @PostMapping()
    public ResponseEntity<ProjectResponseDto> post(@Valid @RequestBody ProjectRequestDto request) {
        ProjectResponseDto response = this.projectService.post(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        return ResponseEntity.created(location).body(response);
    }

    // PUT - 200 OK - 404 Not Found
    @PreAuthorize("hasAuthority('projects:update')")
    @PutMapping("{id}")
    public ResponseEntity<ProjectResponseDto> put(@PathVariable Long id, @RequestBody ProjectRequestDto request) {
        ProjectResponseDto response = this.projectService.put(request, id);
        return ResponseEntity.ok(response);
    }

    // DELETE - 204 No Content - 404 Not Found
    @PreAuthorize("hasAuthority('projects:delete')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
