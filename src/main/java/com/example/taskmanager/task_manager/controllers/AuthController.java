package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.AuthenticationRequestDto;
import com.example.taskmanager.task_manager.dtos.AuthenticationResponseDto;
import com.example.taskmanager.task_manager.dtos.user.UserResponseDto;
import com.example.taskmanager.task_manager.services.IUserService;
import com.example.taskmanager.task_manager.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // POST - 200 OK - 401 Unauthorized
    @PostMapping()
    public ResponseEntity<AuthenticationResponseDto> createToken(@RequestBody AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserResponseDto user = userService.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponseDto(jwtToken));
    }
}
