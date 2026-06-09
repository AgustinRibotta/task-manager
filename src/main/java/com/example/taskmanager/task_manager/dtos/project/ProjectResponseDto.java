package com.example.taskmanager.task_manager.dtos.project;

import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import com.example.taskmanager.task_manager.dtos.user.UserSummaryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Schema(name = "Project", description = "Request for create new project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private UserSummaryDto owner;

    private String description;

    private List<TaskSummaryDto> tasks;

    private Set<UserSummaryDto> users;
}
