package com.example.taskmanager.task_manager.dtos.task;

import java.util.Set;

import com.example.taskmanager.task_manager.dtos.user.UserSummaryDto;
import com.example.taskmanager.task_manager.enums.TaskStatus;

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

    private TaskSummaryDto project;

    private TaskStatus status;

    private Set<UserSummaryDto> users;
}
