package com.example.taskmanager.task_manager.dtos.task;

import java.util.Set;

import com.example.taskmanager.task_manager.dtos.UserDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.enums.TaskStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {

    private Long id; 

    private String name;

    private String description;

    private TaskProjectDto project;

    private TaskStatus status;

    private Set<TaskUserDto> users;
}
