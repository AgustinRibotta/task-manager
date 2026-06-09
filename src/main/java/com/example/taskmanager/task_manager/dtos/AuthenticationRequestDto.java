package com.example.taskmanager.task_manager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Login Request", description = "Request body for authentication")
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequestDto {

    private String username;
    private String password;

}