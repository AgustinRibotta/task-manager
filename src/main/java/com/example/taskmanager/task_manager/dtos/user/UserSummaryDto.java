package com.example.taskmanager.task_manager.dtos.user;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {
    private Long id;

    private String username;

    private String email;

    private Set<RoleRequestDto> roles;
}
