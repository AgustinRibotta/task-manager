package com.example.taskmanager.task_manager.dtos;

import com.example.taskmanager.task_manager.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskDto {
    private Long id;

    private String name;

    private String description;

    private TaskStatus status;

}
