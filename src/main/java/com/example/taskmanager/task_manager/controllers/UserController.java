package com.example.taskmanager.task_manager.controllers;

import java.net.URI;
import java.util.List;

import com.example.taskmanager.task_manager.dtos.user.UserRequestDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.services.IUserService;
import com.example.taskmanager.task_manager.services.imp.ProjectServiceImp;
import com.example.taskmanager.task_manager.services.imp.TaskServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Users")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final ProjectServiceImp projectService;
    private final TaskServiceImp taskService;


    @Operation(summary = "Find all")
    @PreAuthorize("hasAuthority('users:read:all')")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(this.userService.findAll());
        
    }

    @Operation(summary = "Find By id")
    @PreAuthorize("@securytiConfigUser.isUser(#id) or hasAuthority('users:read')")
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto response = this.userService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create New")
    @PreAuthorize("hasAuthority('users:create')")
    @PostMapping()
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto request) {
        UserResponseDto response = this.userService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        
            return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Update")
    @PreAuthorize("hasAuthority('users:update')")
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto request) {
        UserResponseDto response = this.userService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete")
    @PreAuthorize("hasAuthority('users:delete')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        this.projectService.removeUserFromAllProject(id);
        this.taskService.deleteUserFromTask(id);
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ROLES
    @Operation(summary = "Assign Role")
    @PreAuthorize("hasAuthority('users:create')")
    @PostMapping("{userId}/roles")
    public ResponseEntity<?> assignPermission (@PathVariable Long userId, @RequestBody List<Long> request){
        this.userService.assignRoleToUser(userId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove Role")
    @PreAuthorize("hasAuthority('users:create')")
    @DeleteMapping("{userId}/roles/{roleId}")
    public ResponseEntity<?> removePermission (@PathVariable Long userId, @PathVariable Long roleId ){
        this.userService.removeRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }

}
