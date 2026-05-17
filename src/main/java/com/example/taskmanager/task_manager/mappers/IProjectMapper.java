package com.example.taskmanager.task_manager.mappers;

import com.example.taskmanager.task_manager.dtos.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring", uses = { IRoleMapper.class, ITaskMapper.class, IUserForProjectMapper.class })
public interface IProjectMapper {

    @Mapping(source = "users", target = "usersDtos")
    @Mapping(source = "taskEntities", target = "tasksDtos")
    ProjectDto projectEntityToProjecDto(ProjectEntity projectEntity);

    @Mapping(source = "usersDtos", target = "users")
    @Mapping(source = "tasksDtos", target = "taskEntities")
    @Mapping(target = "id", ignore = true)
    ProjectEntity projectDtoToProjectEntity(ProjectDto projecDto);
}