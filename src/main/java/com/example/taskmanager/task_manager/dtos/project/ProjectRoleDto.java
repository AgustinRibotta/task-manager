package com.example.taskmanager.task_manager.dtos.project;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRoleDto {

    private Long id;

    @NotBlank(message = "Username is required")
    private String name;
}
