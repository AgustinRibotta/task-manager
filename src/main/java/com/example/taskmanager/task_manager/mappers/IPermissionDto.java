package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.permission.PermissionDto;
import com.example.taskmanager.task_manager.entities.PermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPermissionDto {

    PermissionDto toPermissionDto (PermissionEntity entity);

    PermissionEntity toPermissionEntity (PermissionDto dto);
}
