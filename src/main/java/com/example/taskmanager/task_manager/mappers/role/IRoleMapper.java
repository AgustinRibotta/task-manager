package com.example.taskmanager.task_manager.mappers.role;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import com.example.taskmanager.task_manager.mappers.IPermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IPermissionDto.class})
public interface IRoleMapper {

    RoleEntity toEntity(RoleRequestDto dto);

    @Mapping(source = "permissions" , target = "permissions")
    RoleResponseDto toDto(RoleEntity entity);
}