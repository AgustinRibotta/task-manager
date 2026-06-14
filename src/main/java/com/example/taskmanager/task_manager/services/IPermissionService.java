package com.example.taskmanager.task_manager.services;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;

import java.util.List;

public interface IPermissionService {
    List<PermissionDto> findAll();
}
