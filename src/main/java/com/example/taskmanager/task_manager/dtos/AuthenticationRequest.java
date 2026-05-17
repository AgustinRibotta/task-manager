package com.example.taskmanager.task_manager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Login Request", description = "Request body for authentication")
@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;

}