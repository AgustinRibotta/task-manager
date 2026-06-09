package com.example.taskmanager.task_manager.dtos.user;

import java.util.Set;

import com.example.taskmanager.task_manager.dtos.project.ProjectSummaryDto;
import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import com.example.taskmanager.task_manager.dtos.role.RoleSummaryDto;
import com.example.taskmanager.task_manager.dtos.task.TaskResponseDto;

import com.example.taskmanager.task_manager.dtos.task.TaskSummaryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "User", description = "Request for create new user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    
    private String username;

    private String password;

    private String email;

    private Set<RoleSummaryDto> roles;

    private Set<ProjectSummaryDto> projects;

    private Set<TaskSummaryDto> tasks;
}