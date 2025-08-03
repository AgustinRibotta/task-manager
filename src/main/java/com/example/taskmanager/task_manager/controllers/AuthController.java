package com.example.taskmanager.task_manager.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    // POST - 200 OK - 401 Unauthorized
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
        final String jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    // GET - 200 OK - []
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
        
    }

    // GET - 200 OK - 404 Not Found
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    // POST - 201 Created - 400 Bad Request - 409 Conflict
    @PostMapping("/users")
    public ResponseEntity<UserDto> post(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.post(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdUser.getId())
            .toUri();
        
            return ResponseEntity.created(location).body(createdUser);
    }
 
    // PUT - 200 OK - 404 Not Found
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> put(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.put(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }
    
    // DELETE - 204 No Content - 404 Not Found
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}