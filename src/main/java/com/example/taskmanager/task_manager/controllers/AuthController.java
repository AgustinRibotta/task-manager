package com.example.taskmanager.task_manager.controllers;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import  com.example.taskmanager.task_manager.dtos.AuthenticationRequest;
import  com.example.taskmanager.task_manager.dtos.AuthenticationResponse;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.services.IUserService;
import  com.example.taskmanager.task_manager.services.JwtService;
import com.example.taskmanager.task_manager.services.imp.ProjectServiceImp;
import com.example.taskmanager.task_manager.services.imp.TaskServiceImp;
import  com.example.taskmanager.task_manager.services.imp.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final IUserService userService;
    private final ProjectServiceImp projectService;
    private final TaskServiceImp taskService;

    // POST - 200 OK - 401 Unauthorized
    @Tag(name = "Login User", description = "Login and token generation")
    @Operation(
            summary = "User login",
            description = "Authenticate user and return a JWT token"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = this.jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    // GET - 200 OK - []
    @Tag(name = "User Controller", description = "User management")
    @Operation(
            summary = "Get all users",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
        
    }

    // GET - 200 OK - 404 Not Found
    @Tag(name = "User Controller", description = "User management")
    @Operation(
            summary = "Get user by ID",
            description = "Requires authentication with JWT and ADMIN role or be the owner"
    )
    @PreAuthorize("@securytiConfigUser.isUser(#id) or hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto userDto = this.userService.getById(id);

        return ResponseEntity.ok(userDto);
    }

    // POST - 201 Creatrd - 400 Bad Request - 409 Conflict
    @Tag(name = "User Controller", description = "User management")
    @Operation(
            summary = "Create new user",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public ResponseEntity<UserDto> post(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.post(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdUser.getId())
            .toUri();
        
            return ResponseEntity.created(location).body(createdUser);
    }
 
    // PUT - 200 OK - 404 Not Found
    @Tag(name = "User Controller", description = "User management")
    @Operation(
            summary = "Update user by ID",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> put(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = this.userService.put(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE - 204 No Content - 404 Not Found
    @Tag(name = "User Controller", description = "User management")
    @Operation(
            summary = "Delete user",
            description = "Requires authentication with JWT and ADMIN role"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        this.projectService.deleteUserFromProjects(id);
        this.taskService.deleteUserFromTask(id);
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
