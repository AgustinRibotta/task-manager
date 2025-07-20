package com.example.taskmanager.task_manager.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

     private Set<RoleDto> roleEntities;
}