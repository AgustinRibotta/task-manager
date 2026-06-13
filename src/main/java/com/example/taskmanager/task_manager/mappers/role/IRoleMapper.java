package com.example.taskmanager.task_manager.mappers.role;

import com.example.taskmanager.task_manager.dtos.role.RoleRequestDto;
import com.example.taskmanager.task_manager.dtos.role.RoleResponseDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    RoleEntity toEntity(RoleRequestDto dto);

    RoleResponseDto toDto(RoleEntity entity);
}