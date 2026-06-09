package com.example.taskmanager.task_manager.controllers;

import java.net.URI;
import java.util.List;

import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
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


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final ProjectServiceImp projectService;
    private final TaskServiceImp taskService;


    // GET - 200 OK - []
    @PreAuthorize("hasAuthority('users:read:all')")
    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(this.userService.findAll());
        
    }

    // GET - 200 OK - 404 Not Found
    @PreAuthorize("@securytiConfigUser.isUser(#id) or hasAuthority('users:read')")
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto response = this.userService.findById(id);

        return ResponseEntity.ok(response);
    }

    // POST - 201 Create - 400 Bad Request - 409 Conflict
    @PreAuthorize("hasAuthority('users:create')")
    @PostMapping()
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserResponseDto request) {
        UserResponseDto response = this.userService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        
            return ResponseEntity.created(location).body(response);
    }
 
    @PreAuthorize("hasAuthority('users:update')")
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserResponseDto request) {
        UserResponseDto response = this.userService.put(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE - 204 No Content - 404 Not Found
    @PreAuthorize("hasAuthority('users:delete')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        this.projectService.removeUserFromAllProject(id);
        this.taskService.deleteUserFromTask(id);
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
