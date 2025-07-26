package com.example.taskmanager.task_manager.controllers;

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

import  com.example.taskmanager.task_manager.dtos.AuthenticationRequest;
import  com.example.taskmanager.task_manager.dtos.AuthenticationResponse;
import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.services.IUserService;
import  com.example.taskmanager.task_manager.services.JwtService;
import  com.example.taskmanager.task_manager.services.imp.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final IUserService userService; 

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

    @GetMapping("/users")
    public List<UserDto> getAll() {
        return userService.getAll();
        
    }

    @GetMapping("/user/{id}")
    public UserDto getMethodName(@PathVariable Long id) {
        return userService.getById(id);
    }
    
    
}