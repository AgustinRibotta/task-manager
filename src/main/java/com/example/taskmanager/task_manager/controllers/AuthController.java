package com.example.taskmanager.task_manager.controllers;

import com.example.taskmanager.task_manager.dtos.auht.AuthenticationRequestDto;
import com.example.taskmanager.task_manager.dtos.auht.AuthenticationResponseDto;
import com.example.taskmanager.task_manager.services.imp.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    // POST - 200 OK - 401 Unauthorized
    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> login(
            @RequestBody AuthenticationRequestDto request){

        AuthenticationResponseDto response =
                authenticationService.login(request);

        return ResponseEntity.ok(response);
    }
}
