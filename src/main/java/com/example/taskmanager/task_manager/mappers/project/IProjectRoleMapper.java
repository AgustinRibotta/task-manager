package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRoleDto;
import com.example.taskmanager.task_manager.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProjectRoleMapper {

    ProjectRoleDto toProjectRoeDto(RoleEntity entity);
}
