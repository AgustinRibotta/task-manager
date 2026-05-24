package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectRequestDto;
import com.example.taskmanager.task_manager.dtos.ProjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring", uses = {IProjectTaskMapper.class, IProjectUserMapper.class})
public interface IProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "taskEntities", ignore = true)
    ProjectEntity requestToEntity(ProjectRequestDto dto);

    @Mapping(source = "taskEntities" , target = "tasksDto")
    @Mapping(source = "users" , target = "usersDto")
    ProjectResponseDto entityToResponse(ProjectEntity entity);
}