package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.ProjectResponseDto;
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
    @Tag(name = "Project Controller", description = "Project management")
    @Operation(
            summary = "Get all project",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponseDto>> getAll() {
        return ResponseEntity.ok(this.projectService.getAll());
    }

    // GET - 200 OK - [] - 401 Unauthorized
    @Tag(name = "Project Controller", description = "Project management")
    @Operation(
            summary = "Get project by ID",
            description = "Requires authentication with JWT and ADMIN role or be the owner"
    )
    @PreAuthorize("@securityConfigProject.isProject(#id) or hasRole('ADMIN')")
    @GetMapping("/project/{id}")
    public ResponseEntity<ProjectResponseDto> getById(@PathVariable Long id) {
        ProjectResponseDto projectDto = this.projectService.getById(id);

        return ResponseEntity.ok(projectDto);
    }

    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @Tag(name = "Project Controller", description = "Project management")
    @Operation(
            summary = "Create new project",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/project/new")
    public ResponseEntity<ProjectResponseDto> post(@Valid @RequestBody ProjectRequestDto projectDto) {

        ProjectResponseDto newProjectDto = this.projectService.post(projectDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newProjectDto.getId())
            .toUri();
        
        return ResponseEntity.created(location).body(newProjectDto);
    }

    // PUT - 200 OK - 404 Not Found
    @Tag(name = "Project Controller", description = "Project management")
    @Operation(
            summary = "Update project by ID",
            description = "Requires authentication with JWT and ADMIN or MANAGER role"
    )
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @PutMapping("project/{id}")
    public ResponseEntity<ProjectResponseDto> put(@PathVariable Long id, @RequestBody ProjectRequestDto projectDto) {

        ProjectResponseDto updateProjectDto = this.projectService.put(projectDto, id);

        return ResponseEntity.ok(updateProjectDto);
    }

    // DELETE - 204 No Content - 404 Not Found
    @Tag(name = "Project Controller", description = "Project management")
    @Operation(
            summary = "Delete project by ID",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/project/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        this.projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@securytiConfigUser.isUser(#id) or hasRole('ADMIN')")
    @GetMapping("/userId/{id}")
    public ResponseEntity<List<ProjectResponseDto>> projectByUserId (@PathVariable Long id) {
        return ResponseEntity.ok(this.projectService.findByUsersId(id));
    }
}
