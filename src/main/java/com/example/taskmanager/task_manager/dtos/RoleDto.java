package com.example.taskmanager.task_manager.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Role", description = "Request for create new role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id; 

    @NotBlank(message = "Username is required")
    private String name;
}
