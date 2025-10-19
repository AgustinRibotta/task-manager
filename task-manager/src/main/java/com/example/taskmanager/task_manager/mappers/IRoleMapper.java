package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.RoleDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    RoleDto roleEntityToRoleDto(RoleEntity roleEntity);

    RoleEntity roleDtoToRoleEntity(RoleDto roleDto);
}