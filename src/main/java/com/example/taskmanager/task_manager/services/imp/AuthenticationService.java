package com.example.taskmanager.task_manager.services.imp;

import com.example.taskmanager.task_manager.dtos.auht.AuthenticationRequestDto;
import com.example.taskmanager.task_manager.dtos.auht.AuthenticationResponseDto;
import com.example.taskmanager.task_manager.entities.UserEntity;
import com.example.taskmanager.task_manager.services.IUserService;
import com.example.taskmanager.task_manager.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IUserService userService;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserEntity user = userService.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);
        return new AuthenticationResponseDto(token);
    }
}