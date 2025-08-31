package com.example.taskmanager.task_manager.dtos;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjecDto {

    private Long id; 

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private List<TaskSummaryDto> tasksDtos;

    private Set<UserSummaryDto> usersDtos;
}
