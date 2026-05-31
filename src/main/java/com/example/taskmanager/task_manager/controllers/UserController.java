package com.example.taskmanager.task_manager.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.task_manager.dtos.user.UserDto;
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
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
        
    }

    // GET - 200 OK - 404 Not Found
    @PreAuthorize("@securytiConfigUser.isUser(#id) or hasAuthority('users:read')")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto response = this.userService.getById(id);

        return ResponseEntity.ok(response);
    }

    // POST - 201 Create - 400 Bad Request - 409 Conflict
    @PreAuthorize("hasAuthority('users:create')")
    @PostMapping()
    public ResponseEntity<UserDto> post(@Valid @RequestBody UserDto request) {
        UserDto response = this.userService.post(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        
            return ResponseEntity.created(location).body(response);
    }
 
    // PUT - 200 OK - 404 Not Found

    @PreAuthorize("hasAuthority('users:update')")
    @PutMapping("{id}")
    public ResponseEntity<UserDto> put(@PathVariable Long id, @RequestBody UserDto request) {
        UserDto response = this.userService.put(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE - 204 No Content - 404 Not Found
    @PreAuthorize("hasAuthority('users:delete')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        this.projectService.deleteUserFromProjects(id);
        this.taskService.deleteUserFromTask(id);
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
