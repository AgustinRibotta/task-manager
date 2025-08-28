package com.example.taskmanager.task_manager.dtos;

import java.util.Set;

import com.example.taskmanager.task_manager.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id; 

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Desciption is required")
    private String description;

    @NotBlank(message = "Project is required")
    private ProjectSummaryDto projecDto;

    @NotBlank(message = "Project is required")
    private TaskStatus status;

    private Set<UserSummaryDto> userSummaryDto;
}
