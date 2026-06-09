package com.example.taskmanager.task_manager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Login Response", description = "Request for authentication and consumer all url")
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String jwtToken;
}