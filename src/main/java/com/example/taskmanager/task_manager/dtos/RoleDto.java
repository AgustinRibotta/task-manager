package com.example.taskmanager.task_manager.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {

    private Long id; 

    @NotBlank(message = "Username is required")
    private String name;
}
