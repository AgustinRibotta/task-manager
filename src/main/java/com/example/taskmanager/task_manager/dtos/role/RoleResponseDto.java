package com.example.taskmanager.task_manager.dtos.role;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {

    private Long id;

    @NotBlank(message = "Username is required")
    private String name;

    @NotBlank
    private Set<PermissionDto> permission;

}
