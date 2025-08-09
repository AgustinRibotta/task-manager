package com.example.taskmanager.task_manager.dtos;

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
    private ProjecDto projecDto;

}
