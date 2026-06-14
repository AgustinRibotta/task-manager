package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;

public interface IRoleService {

    List<RoleResponseDto> getAll();
    RoleResponseDto getById (Long id);
    RoleResponseDto post (RoleRequestDto role);
    RoleResponseDto put (Long id, RoleRequestDto user);
    void delete (Long id);
    // Permission
    void assignPermissionToRole(Long roleId, List<Long> request);
    void removePermissionToRole(Long roleId, Long permissionId);
}
