package com.example.taskmanager.task_manager.services;

import java.util.List;

import com.example.taskmanager.task_manager.dtos.RoleDto;

public interface IRoleService {

    List<RoleDto> getAll();
    RoleDto getById (Long id);
    RoleDto post (RoleDto user);
    RoleDto put (Long id, RoleDto user);
    void delete (Long id);

}
