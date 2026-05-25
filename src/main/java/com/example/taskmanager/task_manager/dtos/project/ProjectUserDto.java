package com.example.taskmanager.task_manager.dtos.project;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserDto {

    private Long id;

    private String username;

    private String email;

    private Set<RoleDto> roles;

}
