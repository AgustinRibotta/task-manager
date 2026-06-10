package com.example.taskmanager.task_manager.dtos.auht;

import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser {
    private Long id;

    private String username;

    private Set<RoleResponseDto> roles;
}
