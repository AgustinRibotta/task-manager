package com.example.taskmanager.task_manager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(hidden = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSummaryDto {
    
    private Long id; 

    private String name;
}
