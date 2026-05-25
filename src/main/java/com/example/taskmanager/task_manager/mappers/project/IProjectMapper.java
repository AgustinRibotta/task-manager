package com.example.taskmanager.task_manager.mappers.project;

import com.example.taskmanager.task_manager.dtos.project.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.project.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring", uses = {IProjectTaskMapper.class, IProjectUserMapper.class})
public interface IProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    ProjectEntity requestToEntity(ProjectRequestDto dto);

    @Mapping(source = "tasks" , target = "tasks")
    @Mapping(source = "users" , target = "users")
    ProjectResponseDto entityToResponse(ProjectEntity entity);
}