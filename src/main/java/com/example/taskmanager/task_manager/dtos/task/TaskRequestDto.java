package com.example.taskmanager.task_manager.dtos.task;

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

import java.util.Set;

@Schema(name = "Task", description = "Request for create new task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "User is required")
    private Set<Long> userId;

    private Long projectId;
}
