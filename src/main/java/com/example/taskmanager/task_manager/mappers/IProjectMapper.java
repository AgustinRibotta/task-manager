package com.example.taskmanager.task_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.taskmanager.task_manager.dtos.ProjecDto;
import com.example.taskmanager.task_manager.entities.ProjectEntity;

@Mapper(componentModel = "spring", uses = { IRoleMapper.class, ITaskMapper.class, IUserForProjectMapper.class })
public interface IProjectMapper {

    @Mapping(source = "users", target = "usersDtos")
    @Mapping(source = "taskEntities", target = "tasksDtos")
    ProjecDto projectEntityToProjecDto(ProjectEntity projectEntity);

    @Mapping(source = "usersDtos", target = "users")
    @Mapping(source = "tasksDtos", target = "taskEntities")
    @Mapping(target = "id", ignore = true)
    ProjectEntity projectDtoToProjectEntity(ProjecDto projecDto);
}