package com.example.taskmanager.task_manager.dtos.project;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Project", description = "Request for create new project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;
}
